package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Polyline extends Shape {
	private ArrayList<Point> points;

	public Polyline(boolean isSolid, double thickness, Color color) {
		super(isSolid, thickness, color);
		this.points = new ArrayList<>();
	}

	public void addPoint(Point point) {
		this.points.add(point);
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void reset() {
		this.points = new ArrayList<>();
	}

	/**
	 * This method draw each shape
	 *
	 * @param g2d
	 */
	@Override
	public void draw(GraphicsContext g2d) {
		g2d.setStroke(this.getColor());
		g2d.setLineWidth(this.getThickness());
		if (points.size() > 1) {
			for (int i = 0; i < points.size() - 1; i++) {
				Point p1 = points.get(i);
				Point p2 = points.get(i + 1);
				g2d.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
			}
		}
	}
}
