package pixelart.geometry;

import java.util.ArrayList;

public class Line {
    private Point start;
    private Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
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
        }
        else {
            double x = end.getX();
            double y = end.getY();
            while (x < start.getX()) {
                steps.add(new Point(x, y));
                x += dx;
                y += dy;
            }
        }

        return steps;
    }
}
