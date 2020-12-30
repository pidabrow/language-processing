package io.pidabrow.languageprocessing.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Node {
    private Token token;
    private List<Node> children;

    public Node(Token token) {
        this.token = token;
        children = new ArrayList<>();
    }

    public Node addChild(Token token) {
        Node child = new Node(token);
        children.add(child);

        return child;
    }
}
