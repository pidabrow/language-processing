package io.pidabrow.languageprocessing.service;

import io.pidabrow.languageprocessing.domain.Node;
import io.pidabrow.languageprocessing.domain.SkipGramWrapper;
import io.pidabrow.languageprocessing.domain.Token;
import io.pidabrow.languageprocessing.domain.Tree;
import io.pidabrow.languageprocessing.dto.InputDto;
import io.pidabrow.languageprocessing.dto.ResultDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@Service
public class SkipGramService {

    public ResultDto generateSkipGrams(InputDto inputDto) {
        List<Token> tokens = getTokens(inputDto.getText());

        int maxGapLength = inputDto.getMaxGapLength();

        Tree skipGramTree = new Tree();
        populateTree(skipGramTree, tokens);
        List<SkipGramWrapper> skipGrams = extractSkipGramsFromTree(skipGramTree);



        return ResultDto.builder()
                .text(inputDto.getText())
                .mode(inputDto.getMode())
                .maxProductLength(inputDto.getMaxProductLength())
                .result(skipGrams.stream().map(SkipGramWrapper::toString).collect(Collectors.toList()))
                .build();
    }

    public void populateTree(Tree tree, List<Token> tokens) {
        populateChildren(tree.getRoot(), tokens);
    }

    private void populateChildren(Node parent, List<Token> tokens) {
        parent.addChild(Tree.NIL_TOKEN, parent);
        tokens.forEach(token -> parent.addChild(token, parent));

        parent.getChildren()
                .forEach(child -> {
                    if(!child.getToken().equals(Tree.NIL_TOKEN)) {
                        int currentId = child.getToken().getTokenId();
                        List<Token> followingTokens = tokens.stream().filter(t -> t.getTokenId() > currentId).collect(Collectors.toList());

                        populateChildren(child, followingTokens);
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
                List<Token> tokens = stack.stream()
                        .filter(Node::isNotRoot)
                        .map(Node::getToken)
                        .collect(Collectors.toList());

                SkipGramWrapper skipGramWrapper = new SkipGramWrapper(tokens);
                System.out.println(skipGramWrapper);
                skipGrams.add(skipGramWrapper);

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
                    List<Token> tokens = stack.stream()
                            .filter(Node::isNotRoot)
                            .map(Node::getToken)
                            .collect(Collectors.toList());

                    SkipGramWrapper skipGramWrapper = new SkipGramWrapper(tokens);
                    System.out.println(skipGramWrapper);
                    skipGrams.add(skipGramWrapper);

                    stack.pop();
                    current = current.getParent();
                    if(current.getToken().equals(Tree.ROOT_TOKEN) && current.getNonVisitedChildByLowestId() == null) {
                        break;
                    }
                }
            }
        }

        return skipGrams;
    }

    private List<Token> getTokens(String text) {
        List<Token> tokens = new ArrayList<>();
        String[] words = text.split(" ");
        for(int i = 0; i < words.length; i++) {
            tokens.add(new Token(words[i], i));
        }
        return tokens;
    }
}
