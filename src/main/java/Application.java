import geometry.Line;
import geometry.Point;
import geometry.Triangle;

public class Application {
    public static void main(String[] args) {
        PNGImage image = new PNGImage(100, 100, new Color(255, 255, 255, 255));

        // Draw seed
        BitPlane plane = new BitPlane(100, 100);
        plane.drawTriangle(new Triangle(
                new Point(10, 10),
                new Point(50, 30),
                new Point(30, 40)
        ));

        image.fromPlane(plane, new Color(255, 0, 0));
        image.write("C:\\Users\\danie\\Pictures\\test.png");
    }
}
