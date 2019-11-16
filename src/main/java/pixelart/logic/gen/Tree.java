package pixelart.logic.gen;

import pixelart.logic.random.Random;
import pixelart.logic.random.Weights;
import pixelart.logic.geometry.Line;
import pixelart.logic.geometry.Point;
import pixelart.logic.geometry.Quad;
import pixelart.logic.geometry.Rect;

import java.util.ArrayList;

public class Tree {
    private Node root;
    private ArrayList<Line> normals;

    // Parameters
    double pUpwardsPull = -0.4;      // Percent to pull the branch upwards. 1 = straight up
    double pAngleChangeMax = 0.05;   // How much the angle can vary from the previous branch
    double pBranchAngleOff = 1;   // Add this angle to a branch when created. Goes either way randomly.
    double pBranchAngleVar = 0.1;   // +- of pBranchAngleOff
    int pMinDepth = 10;           // At least this many nodes must be present from the root
    int pMaxDepth = 30;          // If the tree gets this many nodes deep, stop
    int pBranchDepthStart = 3;   // Minimum depth before a branch can occur
    double pBranchChance = 0.4;     // Chance for a node to spawn a branch
    int pBranchMaxNodes = 2;     // Max nodes in a branch
    double pTerminateChanceBase = 0.0;  // Chance for the node to be the final node on the branch
    double pTerminateChanceMult = 0.05; // How much more terminate chance per later
    int pTerminateChanceOff = 10;    // What depth the terminate chance starts increasing
    double pAvgNodeLength = 5;      // Average length between nodes
    double pVarNodeLength = 2;      // Length between nodes = pAvgNodeLength + [-pVarNodeLength, pVarNodeLength]

    public Tree() {
    }

    public void generate() {
        // Generate tree wire
        root = new Node(new Point(0, 0));
        growNode(root, 0, false, pMaxDepth);
    }

    private void growNode(Node localRoot, int depth, boolean isStartBranch, int nodesLeft) {
        // Chance to terminate the branch randomly
        if (depth >= pMinDepth) {
            double termChance = pTerminateChanceBase;
            if (depth > pTerminateChanceOff) {
                termChance += pTerminateChanceMult * (depth - pTerminateChanceOff);
            }
            Weights<Boolean> terminal = new Weights<Boolean>()
                    .add(true, termChance)
                    .add(false, 1 - termChance);
            if ((Boolean) Random.choice(terminal)) {
                return;
            }
        }

        // Terminate if nodes left is zero
        if (nodesLeft == 0) {
            return;
        }

        // Get angle from local root node
        double prevAngle;
        if (localRoot.getParent() == null) {
            prevAngle = Math.PI / 2;
        } else {
            Line line = new Line(localRoot.getParent().getData(), localRoot.getData());
            prevAngle = line.getAngle();
        }

        // Get normal line
        double NORMAL_LEN = 6;
        double normal = prevAngle + Math.PI / 4;
        double normdx = NORMAL_LEN * Math.cos(normal);
        double normdy = NORMAL_LEN * Math.sin(normal);
        normals.add(new Line(new Point(normdx, normdy), new Point(-normdx, -normdy)));

        // Get angle before being pulled up
        double deltaAngle = Random.getDouble(-pAngleChangeMax, pAngleChangeMax);
        double angle = prevAngle + deltaAngle;

        // Pull angle up
        angle -= Math.PI / 2;
        angle *= (1 - pUpwardsPull);
        angle += Math.PI / 2;

        // Apply branch offset if applicable
        if (isStartBranch) {
            double angleOff = Random.getDouble(pBranchAngleOff - pBranchAngleVar, pBranchAngleOff + pBranchAngleVar);
            if (Random.getInt(0, 1) == 0) {
                angle -= angleOff;
            } else {
                angle += angleOff;
            }
        }

        // Get node length
        double length = Random.getDouble(pAvgNodeLength - pVarNodeLength, pAvgNodeLength + pVarNodeLength);
        double dx = length * Math.cos(angle);
        double dy = length * Math.sin(angle);
        Point newPoint = new Point(localRoot.getData().getX() + dx, localRoot.getData().getY() + dy);
        Node next = new Node(newPoint);
        next.setParent(localRoot);
        growNode(next, depth + 1, false, nodesLeft - 1);

        if (depth >= pBranchDepthStart) {
            Weights<Boolean> weights = new Weights<Boolean>()
                    .add(true, pBranchChance)
                    .add(false, 1 - pBranchChance);
            if ((Boolean) Random.choice(weights)) {
                growNode(next, depth + 1, true, Math.min(nodesLeft - 1, pBranchMaxNodes));
            }
        }
    }

    public ArrayList<Line> getLines() {
        return getLines(root);
    }

    private ArrayList<Line> getLines(Node root) {
        ArrayList<Line> lines = new ArrayList<Line>();

        for (Node node : root.getChildren()) {
            lines.add(new Line(root.getData(), node.getData()));
            lines.addAll(getLines(node));
        }

        return lines;
    }

    //region Getters and Setters
    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public double getpUpwardsPull() {
        return pUpwardsPull;
    }

    public void setpUpwardsPull(double pUpwardsPull) {
        this.pUpwardsPull = pUpwardsPull;
    }

    public double getpAngleChangeMax() {
        return pAngleChangeMax;
    }

    public void setpAngleChangeMax(double pAngleChangeMax) {
        this.pAngleChangeMax = pAngleChangeMax;
    }

    public double getpBranchAngleOff() {
        return pBranchAngleOff;
    }

    public void setpBranchAngleOff(double pBranchAngleOff) {
        this.pBranchAngleOff = pBranchAngleOff;
    }

    public double getpBranchAngleVar() {
        return pBranchAngleVar;
    }

    public void setpBranchAngleVar(double pBranchAngleVar) {
        this.pBranchAngleVar = pBranchAngleVar;
    }

    public int getpMinDepth() {
        return pMinDepth;
    }

    public void setpMinDepth(int pMinDepth) {
        this.pMinDepth = pMinDepth;
    }

    public int getpMaxDepth() {
        return pMaxDepth;
    }

    public void setpMaxDepth(int pMaxDepth) {
        this.pMaxDepth = pMaxDepth;
    }

    public int getpBranchDepthStart() {
        return pBranchDepthStart;
    }

    public void setpBranchDepthStart(int pBranchDepthStart) {
        this.pBranchDepthStart = pBranchDepthStart;
    }

    public double getpBranchChance() {
        return pBranchChance;
    }

    public void setpBranchChance(double pBranchChance) {
        this.pBranchChance = pBranchChance;
    }

    public int getpBranchMaxNodes() {
        return pBranchMaxNodes;
    }

    public void setpBranchMaxNodes(int pBranchMaxNodes) {
        this.pBranchMaxNodes = pBranchMaxNodes;
    }

    public double getpTerminateChanceBase() {
        return pTerminateChanceBase;
    }

    public void setpTerminateChanceBase(double pTerminateChanceBase) {
        this.pTerminateChanceBase = pTerminateChanceBase;
    }

    public double getpTerminateChanceMult() {
        return pTerminateChanceMult;
    }

    public void setpTerminateChanceMult(double pTerminateChanceMult) {
        this.pTerminateChanceMult = pTerminateChanceMult;
    }

    public int getpTerminateChanceOff() {
        return pTerminateChanceOff;
    }

    public void setpTerminateChanceOff(int pTerminateChanceOff) {
        this.pTerminateChanceOff = pTerminateChanceOff;
    }

    public double getpAvgNodeLength() {
        return pAvgNodeLength;
    }

    public void setpAvgNodeLength(double pAvgNodeLength) {
        this.pAvgNodeLength = pAvgNodeLength;
    }

    public double getpVarNodeLength() {
        return pVarNodeLength;
    }

    public void setpVarNodeLength(double pVarNodeLength) {
        this.pVarNodeLength = pVarNodeLength;
    }
    //endregion
}
