package pixelart.gen;

import pixelart.Random;
import pixelart.Weights;
import pixelart.geometry.Line;
import pixelart.geometry.Point;
import pixelart.geometry.Quad;
import pixelart.geometry.Rect;

import java.util.ArrayList;

public class Tree {
    private ArrayList<Quad> trunk;
    private int iterations = 0;

    // Trunk parameters
    double paramBaseWidth = 15;         // How wide the base of the tree is
    double paramLengthVar = 0.01;          // How much +/- the length of a segment varies is from its base length
    double paramAngleDelta = 0.3;       // How much the angle varies on average from the normal of its base (for left branch)
    double paramNarrow = 0.1;           // How much the segment narrows on average
    double paramNarrowVar = 0.05;       // How much +/- the segment is forcibly narrowed
    double repeatDivisor = 2.5;           // Higher number makes smaller segments
    double minSegmentLen = 4;
    double branchChance = 0.1;

    public Tree() {
        trunk = new ArrayList<Quad>();
    }

    public void startGeneration() {
        generate(new Point(-paramBaseWidth / 2, 0), new Point(paramBaseWidth / 2, 0));
    }

    private void generate(Point base1, Point base2) {
        iterations++;
        if (iterations > 30) {
            return;
        }

        // Get base line
        Line base = new Line(base1, base2);
        double baseLen = base.getLength();

        // Get angle of segment
        double deltaAngle = Random.getDouble(-paramAngleDelta, paramAngleDelta);
        double angle = base.getNormal() + deltaAngle;

        // Set left and right angles to narrow by the narrow angle
        double narrowAngle = Random.getDouble(paramNarrow - paramNarrowVar, paramNarrow + paramNarrowVar);
        double leftAngleNarrow = Random.getDouble(0, narrowAngle);
        double rightAngleNarrow = narrowAngle - leftAngleNarrow;

        // Get left and right angles
        double leftAngle = angle - leftAngleNarrow;
        double rightAngle = angle + rightAngleNarrow;

        // Get length of the left and right sides
        double leftLength = Random.getDouble((baseLen/repeatDivisor) - paramLengthVar, (baseLen/repeatDivisor) + paramLengthVar);
        double rightLength = Random.getDouble((baseLen/repeatDivisor) - paramLengthVar, (baseLen/repeatDivisor) + paramLengthVar);
        leftLength = Math.max(leftLength, minSegmentLen);
        rightLength = Math.max(rightLength, minSegmentLen);

        // Get the points of the left and right sides
        double dxL = leftLength * Math.cos(leftAngle);
        double dyL = leftLength * Math.sin(leftAngle);
        double dxR = rightLength * Math.cos(rightAngle);
        double dyR = rightLength * Math.sin(rightAngle);
        Point left = new Point(base1.getX() + dxL, base1.getY() + dyL);
        Point right = new Point(base2.getX() + dxR, base2.getY() + dyR);

        if (baseLen < 3) {
            return;
        }

        // Add quad
        Quad quad = new Quad(base1, base2, right, left);
        trunk.add(quad);
        generate(left, right);
    }

    public Rect getBoundingBox() {
        double minX = trunk.get(0).getP1().getX();
        double maxX = minX;
        double minY = trunk.get(0).getP1().getY();
        double maxY = minY;

        for (Quad q : trunk) {
            if (q.getMinX() < minX)     minX = q.getMinX();
            if (q.getMinY() < minY)     minY = q.getMinY();
            if (q.getMaxX() > maxX)     maxX = q.getMaxX();
            if (q.getMaxY() > maxY)     maxY = q.getMaxY();
        }

        Point origin = new Point(minX - 5, minY);
        Point size = new Point(maxX - minX + 10, maxY - minY + 5);
        return new Rect(origin, size);
    }

    public ArrayList<Quad> getTrunk() {
        return trunk;
    }
}
