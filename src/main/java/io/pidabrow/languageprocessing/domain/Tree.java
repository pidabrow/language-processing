package io.pidabrow.languageprocessing.domain;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Tree {
    Node root;
    private static final Token ROOT_TOKEN = new Token("ROOT", -1);
    private static final Token NIL_TOKEN = new Token("NIL", -1);

    public Tree() {
        root = new Node(ROOT_TOKEN);
    }

    public void populateTree(List<Token> tokens) {
        tokens.stream()
                .forEach(t -> {
                    root.addChild(t);
                });

        root.getChildren()
                .stream()
                .forEach(c -> {
                    int currentId = c.getToken().getTokenId();
                    List<Token> followingTokens = tokens.stream().filter(t -> t.getTokenId() > currentId).collect(Collectors.toList());

                    c.addChild(NIL_TOKEN);
                    followingTokens.stream()
                            .forEach(t -> {
                                c.addChild(t);
                            });
                });

    }

    private void populateSubTree(Node startingNode, List<Token> tokens) {
        tokens.stream()
                .forEach(t -> {
                    startingNode.addChild(t);
                });
    }
}

