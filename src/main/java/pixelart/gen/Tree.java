package pixelart.gen;

import pixelart.Random;
import pixelart.geometry.Point;
import pixelart.geometry.Quad;
import pixelart.geometry.Rect;

import java.util.ArrayList;

public class Tree {
    private ArrayList<Quad> trunk;

    public Tree() {
        trunk = new ArrayList<Quad>();
    }

    public void generate() {
        double baseWidth = 15;
        double widthVar = 3;
        double angleVar = 0.3;

        double angle = 1.5708;  //90 degrees
        Point p1 = new Point(50 - baseWidth / 2, 0);
        Point p2 = new Point(50 + baseWidth / 2, 0);
        double deltaAngle1 = Random.getDouble(-angleVar, angleVar);
        angle += deltaAngle1;
        double deltaPos1 = Random.getDouble(baseWidth - widthVar, baseWidth + widthVar);
        double deltaX1 = deltaPos1 * Math.cos(angle);
        double deltaY1 = deltaPos1 * Math.sin(angle);
        Point p4 = new Point(p1.getX() + deltaX1, p1.getY() + deltaY1);
        double deltaPos2 = Random.getDouble(baseWidth - widthVar, baseWidth + widthVar);
        double deltaX2 = deltaPos2 * Math.cos(angle);
        double deltaY2 = deltaPos2 * Math.sin(angle);
        Point p3 = new Point(p2.getX() + deltaX2, p2.getY() + deltaY2);

        trunk.add(new Quad(p1, p2, p3, p4));
    }

    public Rect getBoundingBox() {
        double minX = trunk.get(0).getP1().getX();
        double maxX = minX;
        double minY = trunk.get(0).getP1().getY();
        double maxY = minY;

        for (Quad q : trunk) {
            minX = Math.min(minX, q.getP1().getX());
            minX = Math.min(minX, q.getP2().getX());
            minX = Math.min(minX, q.getP3().getX());
            minX = Math.min(minX, q.getP4().getX());

            minY = Math.min(minY, q.getP1().getY());
            minY = Math.min(minY, q.getP2().getY());
            minY = Math.min(minY, q.getP3().getY());
            minY = Math.min(minY, q.getP4().getY());

            maxX = Math.max(maxX, q.getP1().getX());
            maxX = Math.max(maxX, q.getP2().getX());
            maxX = Math.max(maxX, q.getP3().getX());
            maxX = Math.max(maxX, q.getP4().getX());

            maxY = Math.max(maxY, q.getP1().getY());
            maxY = Math.max(maxY, q.getP2().getY());
            maxY = Math.max(maxY, q.getP3().getY());
            maxY = Math.max(maxY, q.getP4().getY());
        }

        Point origin = new Point(minX, minY);
        Point size = new Point(maxX - minX, maxY - minY);
        return new Rect(origin, size);
    }

    public ArrayList<Quad> getTrunk() {
        return trunk;
    }
}
