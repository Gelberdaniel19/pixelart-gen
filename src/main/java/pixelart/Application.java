package pixelart;

import pixelart.gen.Tree;
import pixelart.geometry.Line;
import pixelart.geometry.Point;
import pixelart.geometry.Quad;
import pixelart.geometry.Rect;

import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Tree tree = new Tree();
            tree.generate();
            ArrayList<Line> lines = tree.getLines();

            int width = 100;
            int height = 130;

            PNGImage image = new PNGImage(width, height, new Color(255, 255, 255));
            BitPlane plane = new BitPlane(width, height);

            for (Line line : lines) {
                Line trans = new Line(Point.addX(line.getStart(), 50), Point.addX(line.getEnd(), 50));
                plane.drawLine(trans);
            }

            image.fromPlane(plane, new Color(0, 0, 0));
            image.write("C:\\Users\\Daniel\\Pictures\\test" + i + ".png");
        }
    }
}
