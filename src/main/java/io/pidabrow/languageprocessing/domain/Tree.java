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
        populateChildren(this.root, tokens);
    }

    private void populateChildren(Node parent, List<Token> tokens) {
        parent.addChild(NIL_TOKEN);
        tokens.stream()
                .forEach(t -> {
                    parent.addChild(t);
                });

        parent.getChildren()
                .stream()
                .forEach(child -> {
                    if(!child.getToken().equals(NIL_TOKEN)) {
                        int currentId = child.getToken().getTokenId();
                        List<Token> followingTokens = tokens.stream().filter(t -> t.getTokenId() > currentId).collect(Collectors.toList());

                        populateChildren(child, followingTokens);
                    } else {
                        System.out.println("NIL token. Parent: " + parent);
                    }
                });
    }
}

