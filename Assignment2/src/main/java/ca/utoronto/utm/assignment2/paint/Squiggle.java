package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;

public class Squiggle {
    private ArrayList<Point> points;

    public Squiggle() {
        this.points = new ArrayList<>();
    }

    public void addPoint(Point point) {
        this.points.add(point);
    }

    public ArrayList<Point> getPoints() {
        return this.points;
    }
}