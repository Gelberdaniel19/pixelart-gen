package geometry;

public class Rectangle {
    private Point x;
    private Point y;
    private Point w;
    private Point h;

    public Rectangle(Point x, Point y, Point w, Point h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    //region Getters and Setters
    public Point getX() {
        return x;
    }

    public void setX(Point x) {
        this.x = x;
    }

    public Point getY() {
        return y;
    }

    public void setY(Point y) {
        this.y = y;
    }

    public Point getW() {
        return w;
    }

    public void setW(Point w) {
        this.w = w;
    }

    public Point getH() {
        return h;
    }

    public void setH(Point h) {
        this.h = h;
    }
    //endregion
}
