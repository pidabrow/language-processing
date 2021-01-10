package io.pidabrow.languageprocessing.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Node {
    private Node parent;
    private final Token token;
    private final List<Node> children;
    private final int depth;

    private boolean visited;

    public Node(Token token) {
        this.token = token;
        this.children = new ArrayList<>();
        this.depth = 0;
    }

    public Node(Token token, Node parent) {
        this.token = token;
        this.parent = parent;
        this.children = new ArrayList<>();
        this.depth = this.parent.getDepth() + 1;
    }

    public void addChild(Token token, Node parent) {
        Node child = new Node(token, parent);
        this.children.add(child);
    }

    public boolean nonVisitedChildExists() {
        return  !children.stream()
                .filter(c -> !c.isVisited() && c.isNotNil())
                .collect(Collectors.toList())
                .isEmpty();
    }

    public Node getNonVisitedChildByLowestId() {
        return children.stream()
                .filter(c -> !c.isVisited() && c.isNotNil())
                .min(Comparator.comparing(x -> x.getToken().getTokenId()))
                .orElse(new Node(Tree.NIL_TOKEN, this));
    }

    public void markVisited() {
        this.visited = true;
    }

    public boolean isRoot() {
        return this.getToken().equals(Tree.ROOT_TOKEN);
    }

    public boolean isNotRoot() {
        return !isRoot();
    }

    public boolean isNil() {
        return this.getToken().equals(Tree.NIL_TOKEN);
    }

    public boolean isNotNil() {
        return !isNil();
    }

    @Override
    public String toString() {
        return "Node{" +
                "tokenId=" + token.getTokenId() +
                ", tokenValue=" + token.getValue() +
                ", children=" + children.stream().map(Node::getToken).collect(Collectors.toList()) +
                '}';
    }
}
