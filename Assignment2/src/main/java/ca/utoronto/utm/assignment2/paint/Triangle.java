package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends Shape {
	private Point pointA;
	private Point pointB;
	private Point pointC;

	public Triangle(Point pointA, Point pointB, Point pointC, boolean isSolid, double thickness, Color color) {
		super(isSolid, thickness, color);
		this.pointA = pointA;
		this.pointB = pointB;
		this.pointC = pointC;
	}

	public Point getPointA() {
		return pointA;
	}

	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}

	public Point getPointB() {
		return pointB;
	}

	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}

	public Point getPointC() {
		return pointC;
	}

	public void setPointC(Point pointC) {
		this.pointC = pointC;
	}

	public void draw(GraphicsContext g2d) {
		// Draw the filled triangle

		if (this.isSolid()) {
			g2d.setFill(this.getColor());
			g2d.fillPolygon(
					new double[]{pointA.getX(), pointB.getX(), pointC.getX()},
					new double[]{pointA.getY(), pointB.getY(), pointC.getY()},
					3
			);
		} else {
			g2d.setStroke(this.getColor());
			g2d.setLineWidth(this.getThickness());
			g2d.strokePolygon(
					new double[]{pointA.getX(), pointB.getX(), pointC.getX()},
					new double[]{pointA.getY(), pointB.getY(), pointC.getY()},
					3
			);
		}
	}
}
