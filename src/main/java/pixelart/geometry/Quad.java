package pixelart.geometry;

import java.util.ArrayList;

public class Quad {
    private Point p1;
    private Point p2;
    private Point p3;
    private Point p4;

    public Quad(Point p1, Point p2, Point p3, Point p4) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    //region Getters and Setters
    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public Point getP3() {
        return p3;
    }

    public void setP3(Point p3) {
        this.p3 = p3;
    }

    public Point getP4() {
        return p4;
    }

    public void setP4(Point p4) {
        this.p4 = p4;
    }
    //endregion

    public ArrayList<Triangle> getTriangles() {
        ArrayList<Triangle> out = new ArrayList<Triangle>();
        out.add(new Triangle(p1, p2, p3));
        out.add(new Triangle(p1, p3, p4));
        return out;
    }

    public Point getMiddle() {
        double xMid = p1.getX() + p2.getX() + p3.getX() + p4.getX();
        xMid /= 4;
        double yMid = p1.getY() + p2.getY() + p3.getY() + p4.getY();
        yMid /= 4;

        return new Point(xMid, yMid);
    }
}
