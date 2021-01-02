package io.pidabrow.languageprocessing.service;

import io.pidabrow.languageprocessing.domain.Node;
import io.pidabrow.languageprocessing.domain.SkipGramContainer;
import io.pidabrow.languageprocessing.domain.Token;
import io.pidabrow.languageprocessing.domain.Tree;
import io.pidabrow.languageprocessing.dto.InputDto;
import io.pidabrow.languageprocessing.dto.ResultDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@Service
public class SkipGramService {

    public ResultDto generateSkipGrams(InputDto inputDto) {
        List<String> skipGrams = new ArrayList<>();

        String[] words = inputDto.getText().split(" ");
        List<Token> tokens = new ArrayList<>();
        for(int i = 0; i < words.length; i++) {
            tokens.add(new Token(words[i], i));
        }

        int maxGapLength = inputDto.getMaxGapLength();

        Tree skipGramTree = new Tree();
        populateTree(skipGramTree, tokens);
        extractSkipGramsFromTree(skipGramTree);

        return ResultDto.builder()
                .text(inputDto.getText())
                .mode(inputDto.getMode())
                .maxProductLength(inputDto.getMaxProductLength())
                .result(skipGrams)
                .build();
    }

    public Tree populateTree(Tree tree, List<Token> tokens) {
        populateChildren(tree.getRoot(), tokens);

        return tree;
    }

    private void populateChildren(Node parent, List<Token> tokens) {
        parent.addChild(Tree.NIL_TOKEN, parent);
        tokens.stream()
                .forEach(token -> {
                    parent.addChild(token, parent);
                });

        parent.getChildren()
                .stream()
                .forEach(child -> {
                    if(!child.getToken().equals(Tree.NIL_TOKEN)) {
                        int currentId = child.getToken().getTokenId();
                        List<Token> followingTokens = tokens.stream().filter(t -> t.getTokenId() > currentId).collect(Collectors.toList());

                        populateChildren(child, followingTokens);
                    }
                });
    }

    public List<SkipGramContainer> extractSkipGramsFromTree(Tree tree) {
        List<SkipGramContainer> skipGrams = new ArrayList<>();
        Stack<Node> stack = new Stack<>();

        Node current = tree.getRoot();
        current.markVisited();
        stack.push(current);

        while(!stack.isEmpty()) {
            if(current.nonVisitedChildExists()) {
                current = current.getNonVisitedChildByLowestId();
            } else {
                List<Token> tokens = stack.stream()
                        .filter(e -> e.isNotRoot())
                        .map(Node::getToken)
                        .collect(Collectors.toList());

                SkipGramContainer skipGramContainer = new SkipGramContainer(tokens);
                System.out.println(skipGramContainer);
                skipGrams.add(skipGramContainer);

                stack.pop();
                current = current.getParent();
                continue;
                // todo: push to stack?
            }

            if(current.isNotNil()) {
                if(current.isNotRoot()) {
                    current.markVisited();
                    stack.push(current);
                }
            } else {
                while(current.getParent().getNonVisitedChildByLowestId().isVisited() != false) {
                    List<Token> tokens = stack.stream()
                            .filter(e -> e.isNotRoot())
                            .map(Node::getToken)
                            .collect(Collectors.toList());

                    SkipGramContainer skipGramContainer = new SkipGramContainer(tokens);
                    System.out.println(skipGramContainer);
                    skipGrams.add(skipGramContainer);

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
}
