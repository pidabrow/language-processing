package io.pidabrow.languageprocessing.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
public class Node {
    private Node parent;
    private Token token;
    private List<Node> children;

    private boolean visited;

    public Node(Token token) {
        this.token = token;
        this.children = new ArrayList<>();
    }

    public Node(Token token, Node parent) {
        this.token = token;
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    public Node addChild(Token token, Node parent) {
        Node child = new Node(token, parent);
        this.children.add(child);

        return child;
    }

    public boolean nonVisitedChildExists() {
        return  !children.stream()
                .filter(c -> !c.isVisited() && c.isNotNil())
                .collect(Collectors.toList())
                .isEmpty();
    }

    public Node getNonVisitedChildByLowestId() {
        return children.stream()
                .filter(c -> c.isVisited() == false && c.isNotNil())
                .min(Comparator.comparing(x -> x.getToken().getTokenId()))
                .orElse(new Node(Tree.NIL_TOKEN, this)); // todo: verify this
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
