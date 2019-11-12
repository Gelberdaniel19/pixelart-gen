package pixelart;

import pixelart.gen.Tree;
import pixelart.geometry.Point;
import pixelart.geometry.Quad;

public class Application {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            PNGImage image = new PNGImage(100, 100, new Color(255, 255, 255, 255));

            BitPlane plane = new BitPlane(100, 100);
            Tree tree = new Tree();
            plane.drawQuad(tree.generate());

            image.fromPlane(plane, new Color(0, 0, 0));
            image.write("C:\\Users\\Daniel\\Pictures\\test" + i + ".png");
        }
    }
}
