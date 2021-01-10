package io.pidabrow.languageprocessing.service;

import io.pidabrow.languageprocessing.domain.Node;
import io.pidabrow.languageprocessing.domain.SkipGramWrapper;
import io.pidabrow.languageprocessing.domain.Token;
import io.pidabrow.languageprocessing.domain.Tree;
import io.pidabrow.languageprocessing.dto.InputDto;
import io.pidabrow.languageprocessing.dto.ResultDto;
import io.pidabrow.languageprocessing.enumeration.Mode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
// no need for transaction management, since there's no DB connection currently. It could be also a @Component
public class SkipGramService {

    public ResultDto generateSkipGrams(InputDto inputDto) {
        List<Token> tokens = extractTokensFromText(inputDto.getText());
        int maxTreeDepth = inputDto.getMaxProductLength();

        Tree skipGramTree = new Tree(maxTreeDepth);
        populateTree(skipGramTree, tokens);
        List<SkipGramWrapper> skipGrams = extractSkipGramsFromTree(skipGramTree);

        return ResultDto.builder()
                .text(inputDto.getText())
                .mode(Mode.SKIP_GRAM)
                .maxProductLength(inputDto.getMaxProductLength())
                .result(skipGrams.stream().map(SkipGramWrapper::toString).collect(Collectors.toList()))
                .build();
    }

    public void populateTree(Tree tree, List<Token> tokens) {
        populateChildren(tree.getRoot(), tree.getMaxTreeDepth(), tokens);
    }

    private void populateChildren(Node parent, int treeMaxDepth, List<Token> tokens) {
        parent.addChild(Tree.NIL_TOKEN, parent);
        tokens.forEach(token -> parent.addChild(token, parent));

        parent.getChildren()
                .forEach(child -> {
                    if(child.isNotNil() && child.getDepth() < treeMaxDepth) {
                        int currentId = child.getToken().getTokenId();
                        List<Token> followingTokens = tokens.stream().filter(t -> t.getTokenId() > currentId).collect(Collectors.toList());

                        populateChildren(child, treeMaxDepth, followingTokens);
                    }
                });
    }

    public List<SkipGramWrapper> extractSkipGramsFromTree(Tree tree) {
        List<SkipGramWrapper> skipGrams = new ArrayList<>();
        Stack<Node> stack = new Stack<>();

        Node current = tree.getRoot();
        current.markVisited();
        stack.push(current);

        while(!stack.isEmpty()) {
            if(current.nonVisitedChildExists()) {
                current = current.getNonVisitedChildByLowestId();
            } else {
                List<Token> tokens = getAllTokensFromStack(stack);
                if(!tokens.isEmpty()) {
                    SkipGramWrapper skipGramWrapper = new SkipGramWrapper(tokens);
                    System.out.println(skipGramWrapper);
                    skipGrams.add(skipGramWrapper);
                }

                stack.pop();
                current = current.getParent();
                continue;
            }

            if(current.isNotNil()) {
                if(current.isNotRoot()) {
                    current.markVisited();
                    stack.push(current);
                }
            } else {
                while(current.getParent().getNonVisitedChildByLowestId().isVisited()) {
                    List<Token> tokens = getAllTokensFromStack(stack);
                    SkipGramWrapper skipGramWrapper = new SkipGramWrapper(tokens);
                    System.out.println(skipGramWrapper);
                    skipGrams.add(skipGramWrapper);

                    stack.pop();
                    current = current.getParent();
                    if(current.isRoot() && current.getNonVisitedChildByLowestId() == null) {
                        break;
                    }
                }
            }
        }

        return skipGrams;
    }

    private List<Token> getAllTokensFromStack(Stack<Node> stack) {
        return stack.stream()
                .filter(Node::isNotRoot)
                .map(Node::getToken)
                .collect(Collectors.toList());
    }

    private List<Token> extractTokensFromText(String text) {
        List<Token> tokens = new ArrayList<>();
        String[] words = text.split(" ");

        IntStream.range(0, words.length).forEach(i -> {
            tokens.add(new Token(words[i], i));
        });

        return tokens;
    }
}
