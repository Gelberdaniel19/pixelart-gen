package pixelart.logic.geometry;

public class Rect {
    private Point origin;
    private Point size;

    public Rect(Point origin, Point size) {
        this.origin = origin;
        this.size = size;
    }

    public Point getOrigin() {
        return origin;
    }

    public Point getNegativeOrigin() {
        return new Point(-origin.getX(), -origin.getY());
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    public Point getSize() {
        return size;
    }

    public void setSize(Point size) {
        this.size = size;
    }
}
