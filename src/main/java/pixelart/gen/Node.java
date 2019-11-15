package pixelart.gen;

import pixelart.geometry.Point;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Point data;
    private Node parent = null;
    private List<Node> children;

    public Node(Point data) {
        this.data = data;
        children = new ArrayList<Node>();
    }

    public void setParent(Node parent) {
        this.parent = parent;
        parent.addChild(this);
    }

    public Point getData() {
        return data;
    }

    public Node getParent() {
        return parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public Node addChild(Node node) {
        children.add(node);
        return this;
    }
}
