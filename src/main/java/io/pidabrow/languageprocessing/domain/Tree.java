package io.pidabrow.languageprocessing.domain;

import lombok.Getter;

@Getter
public class Tree {
    public static final Token ROOT_TOKEN = new Token("ROOT", -1);
    public static final Token NIL_TOKEN = new Token("NIL", -1);

    private final Node root;

    public Tree() {
        root = new Node(ROOT_TOKEN);
    }
}

