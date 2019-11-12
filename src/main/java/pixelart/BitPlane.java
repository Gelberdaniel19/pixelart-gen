package pixelart;

import pixelart.geometry.Line;
import pixelart.geometry.Point;
import pixelart.geometry.Quad;
import pixelart.geometry.Triangle;

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

    //region Getters and Setters
    public Bit get(int x, int y) {
        return plane.get(x * width + y);
    }

    public Bit get(Bit bit) {
        return get(bit.x, bit.y);
    }

    public Bit set(int x, int y, boolean on) {
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

    public ArrayList<Bit> getBits() {
        return plane;
    }
    //endregion

    /**
     * Recursively fill the area starting with this bit
     * @param bit   bit to start filling with
     */
    public void fill(Bit bit) {
        // Base case
        if (get(bit).on) {
            return;
        }

        // Action
        set(bit, true);

        // Recursive case
        System.out.println("pixelart.Bit: " + bit.x + ", " + bit.y);
        if (!get(bit.up()).on)      fill(bit.up());
        if (!get(bit.down()).on)    fill(bit.down());
        if (!get(bit.left()).on)    fill(bit.left());
        if (!get(bit.right()).on)   fill(bit.right());
    }

    public void fill(int x, int y) {
        fill(get(x, y));
    }

    public ArrayList<Bit> drawLine(Line l) {
        ArrayList<Bit> out = new ArrayList<Bit>();

        for (Point p : l.getSteps()) {
            Bit bit = new Bit((int)p.getX(), (int)p.getY());
            out.add(bit);
            set(bit, true);
        }

        return out;
    }

    public void drawTriangle(Triangle t) {
        ArrayList<Bit> line1 = drawLine(new Line(t.getP1(), t.getP2()));
        ArrayList<Bit> line2 = drawLine(new Line(t.getP2(), t.getP3()));
        ArrayList<Bit> line3 = drawLine(new Line(t.getP3(), t.getP1()));

        Point mid = t.getMiddle();
        fill((int)mid.getX(), (int)mid.getY());
    }

    public void drawQuad(Quad q) {
        ArrayList<Bit> line1 = drawLine(new Line(q.getP1(), q.getP2()));
        ArrayList<Bit> line2 = drawLine(new Line(q.getP2(), q.getP3()));
        ArrayList<Bit> line3 = drawLine(new Line(q.getP3(), q.getP4()));
        ArrayList<Bit> line4 = drawLine(new Line(q.getP4(), q.getP1()));

        Point mid = q.getMiddle();
        fill((int)mid.getX(), (int)mid.getY());
    }
}
