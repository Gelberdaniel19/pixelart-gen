package pixelart;

import pixelart.gen.Tree;
import pixelart.geometry.Line;
import pixelart.geometry.Point;
import pixelart.geometry.Quad;
import pixelart.geometry.Rect;

public class Application {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Tree tree = new Tree();
            tree.startGeneration();
            Rect box = tree.getBoundingBox();

            int width = (int)box.getSize().getX() + 1;
            int height = (int)box.getSize().getY() + 1;

            PNGImage image = new PNGImage(width, height, new Color(255, 255, 255, 255));
            BitPlane plane = new BitPlane(width, height);

            for (Quad q : tree.getTrunk()) {
                Quad trans = q.translate(box.getNegativeOrigin());
                plane.drawQuad(trans);
            }

            plane.fillHoles();
            image.fromPlane(plane, new Color(0, 0, 0));
            image.write("C:\\Users\\Daniel\\Pictures\\test" + i + ".png");
            System.out.println("\n\n");
        }
    }
}
