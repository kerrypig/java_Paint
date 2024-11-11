package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends Shape {
	private Point pointA;
	private Point pointB;
	private Point pointC;

	public Triangle(Point pointA, Point pointB, Point pointC, boolean isSolid, double thickness, Color color) {
		super(isSolid, thickness, color,new Point(0,0),0,0);
		this.pointA = pointA;
		this.pointB = pointB;
		this.pointC = pointC;
		super.setCenter(new Point((int) Math.min(Math.min(pointA.getX() , pointC.getX()),pointB.getX())
				, (int) Math.min(Math.min(pointA.getY() , pointC.getY()),pointB.getY())));
		super.setLength_x(Math.abs(((int) Math.min(Math.min(pointA.getX() , pointC.getX()),pointB.getX()))-((int) Math.max(Math.max(pointA.getX() , pointC.getX()),pointB.getX()))));
		super.setLength_y(Math.abs(((int) Math.min(Math.min(pointA.getY() , pointC.getY()),pointB.getY()))-((int) Math.max(Math.max(pointA.getY() , pointC.getY()),pointB.getY()))));

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

			public void move( double x, double y) {
				setPointA(new Point(pointA.getX() + x, pointA.getY() + y));
				setPointB(new Point(pointB.getX() + x, pointB.getY() + y));
				setPointC(new Point(pointC.getX() + x, pointC.getY() + y));
				super.setCenter(new Point((int) Math.min(Math.min(pointA.getX() , pointC.getX()),pointB.getX())+x
						, (int) Math.min(Math.min(pointA.getY() , pointC.getY()),pointB.getY())+y));
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
