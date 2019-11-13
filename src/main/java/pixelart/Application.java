package pixelart;

import pixelart.gen.Tree;
import pixelart.geometry.Point;
import pixelart.geometry.Quad;
import pixelart.geometry.Rect;

public class Application {
    public static void main(String[] args) {
        PNGImage image = new PNGImage(100, 10, new Color(255, 255, 255, 255));
        BitPlane plane = new BitPlane(100, 10);
        for (int i = 5; i < 10; i++) {
            for (int j = 5; j < 10; j++) {
                plane.set(i, j, true);
            }
        }
        image.fromPlane(plane, new Color(0, 0, 0));
        image.write("C:\\Users\\Daniel\\Pictures\\test.png");

//        for (int i = 0; i < 10; i++) {
//            Tree tree = new Tree();
//            tree.generate();
//            Rect box = tree.getBoundingBox();
//
//            PNGImage image = new PNGImage((int)box.getSize().getX(), (int)box.getSize().getY(), new Color(255, 255, 255, 255));
//            BitPlane plane = new BitPlane((int)box.getSize().getX(), (int)box.getSize().getY());
//
//            for (Quad q : tree.getTrunk())
//                plane.drawQuad(q.translate(box.getNegativeOrigin()));
//
//            image.fromPlane(plane, new Color(0, 0, 0));
//            image.write("C:\\Users\\Daniel\\Pictures\\test" + i + ".png");
//        }
    }
}
