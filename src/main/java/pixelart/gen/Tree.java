package pixelart.gen;

import pixelart.Random;
import pixelart.geometry.Point;
import pixelart.geometry.Quad;
import java.util.ArrayList;

public class Tree {
    private ArrayList<Quad> trunk;

    public Tree() {
        trunk = new ArrayList<Quad>();
    }

    public Quad generate() {
        int width = 100;
        int height = 100;

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

        return new Quad(p1, p2, p3, p4);
    }
}
