package pixelart.logic.geometry;

import java.util.ArrayList;

public class Line {
    private Point start;
    private Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    private static boolean isBetween(double n, double a, double b) {
        return (n < a && n > b) || (n < b && n > a);
    }

    //region Getters and Setters
    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }
    //endregion

    public ArrayList<Point> getSteps() {
        ArrayList<Point> steps = new ArrayList<Point>();

        double theta = Math.atan((end.getY() - start.getY()) / (end.getX() - start.getX()));
        double dx = Math.cos(theta);
        double dy = Math.sin(theta);


        if (start.getX() < end.getX()) {
            double x = start.getX();
            double y = start.getY();
            while (x < end.getX()) {
                steps.add(new Point(x, y));
                x += dx;
                y += dy;
            }
            steps.add(end);
        }
        else {
            double x = end.getX();
            double y = end.getY();
            while (x < start.getX()) {
                steps.add(new Point(x, y));
                x += dx;
                y += dy;
            }
            steps.add(start);
        }

        return steps;
    }

    public double getLength() {
        return Math.sqrt(Math.pow(end.getX() - start.getX(), 2) + Math.pow(end.getY() - start.getY(), 2));
    }

    public double getNormal() {
        double dx = end.getX() - start.getX();
        double dy = end.getY() - start.getY();

        Point normalVec;
        if (dy > 0) {
            normalVec = new Point(-dy, dx);
        } else {
            normalVec = new Point(-dy, dx);
        }

        if (normalVec.getX() == 0) {
            return Math.PI / 2;
        }

        double angle = Math.atan(normalVec.getY() / normalVec.getX());
        if (angle < 0) {
            angle = Math.PI + angle;
        }
        return angle;
    }

    public double getAngle() {
        double dx = end.getX() - start.getX();
        double dy = end.getY() - start.getY();

        double angle = Math.atan(dy / dx);
        if (dx < 0) {
            angle += Math.PI;
        }

        return angle;
    }
}
