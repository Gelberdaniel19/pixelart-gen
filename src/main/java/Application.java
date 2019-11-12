public class Application {
    public static void main(String[] args) {
        PNGImage image = new PNGImage(100, 100, new Color(255, 255, 255, 255));

        // Draw a diagonal line
        BitPlane plane = new BitPlane(100, 100);
        Bit b = plane.set(0, 0, true);
        while ((b = plane.set(b.up().right(), true)) != null);
        image.fromPlane(plane, new Color(130, 90, 100));

        Weights<Integer> weights = new Weights()
                .add(1, 0.1)
                .add(2, 0.1)
                .add(3, 0.1)
                .add(4, 0.7);
        for (int i = 0; i < 50; i++) {
            int n = Random.choice(weights);
            System.out.println(n);
        }

        image.write("C:\\Users\\danie\\Pictures\\test.png");
    }
}
