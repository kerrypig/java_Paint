package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Squiggle extends Shape{
	private ArrayList<Point> points;

	public Squiggle(boolean isSolid, double thickness, Color color) {
        super(isSolid, thickness, color);
		this.points = new ArrayList<>();
	}

	public void addPoint(Point point) {
		this.points.add(point);
	}

	public ArrayList<Point> getPoints() {
		return this.points;
	}

    /**
     * This method draw each shape
     * @param g2d
     */
    @Override
    public void draw(GraphicsContext g2d) {
        ArrayList<Point> squigglePoints = this.getPoints();
        for (int i = 0; i < squigglePoints.size() - 1; i++) {
            Point p1 = squigglePoints.get(i);
            Point p2 = squigglePoints.get(i + 1);
            g2d.setStroke(this.getColor());
            g2d.setLineWidth(this.getThickness());
            g2d.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        }
    }
}