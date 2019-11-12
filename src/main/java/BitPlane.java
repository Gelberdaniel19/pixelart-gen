import java.util.ArrayList;

public class BitPlane {
    private int width;
    private int height;
    private ArrayList<Bit> plane;

    public BitPlane(int width, int height) {
        this.width = width;
        this.height = height;

        plane = new ArrayList<Bit>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                plane.add(new Bit(i, j));
            }
        }
    }

    public Bit get(int x, int y) {
        return plane.get(x * width + y);
    }

    public Bit set(int x, int y, boolean on) {
        System.out.println(x + ", " + y);

        int index = x * width + y;
        if (index < 0 || index > plane.size()) {
            return null;
        }

        plane.get(index).on = on;
        return plane.get(index);
    }

    public Bit set(Bit bit, boolean on) {
        if (bit.x >= width || bit.y >= height) {
            return null;
        }

        return set(bit.x, bit.y, on);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
