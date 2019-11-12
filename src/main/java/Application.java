public class Application {
    public static void main(String[] args) {
        PNGImage image = new PNGImage(100, 100, new Color(255, 255, 255, 255));

        BitPlane plane = new BitPlane(100, 100);
        Bit b = plane.set(0, 0, true);
        while ((b = plane.set(b.up().right(), true)) != null);

        image.fromPlane(plane, new Color(130, 90, 100));

        image.write("C:\\Users\\Daniel\\Pictures\\test.png");
    }
}
