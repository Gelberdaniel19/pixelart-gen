package pixelart.geometry;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public static Point add(Point p, Point q) {
        return new Point(p.getX() + q.getX(), p.getY() + q.getY());
    }

    public static Point addX(Point p, double x) {
        return new Point(p.getX() + x, p.getY());
    }

    public static Point addY(Point p, double y) {
        return new Point(p.getX(), p.getY() + y);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
