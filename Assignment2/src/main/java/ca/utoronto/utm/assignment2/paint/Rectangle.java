package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape {
	private Point left_up, right_down, constant_corner;


	public Rectangle(Point p1, Point p2, boolean isSolid, double thickness, Color color) {
		//p1: corner 1, p2: corner 2
		super(isSolid, thickness, color);
		this.left_up = p1;
		this.right_down = p2;
		this.constant_corner = p1;
	}


	private Point getMax(Point num1, Point num2) {
		double x1 = num1.getX();
		double y1 = num1.getY();
		double x2 = num2.getX();
		double y2 = num2.getY();
		Point out = new Point(Math.max(x1, x2), Math.max(y1, y2));
		return out;
	}

	private Point getMin(Point num1, Point num2) {
		double x1 = num1.getX();
		double y1 = num1.getY();
		double x2 = num2.getX();
		double y2 = num2.getY();
		Point out = new Point(Math.min(x1, x2), Math.min(y1, y2));
		return out;
	}

	public void setLeft_up(Point p1) {
		this.left_up = p1;
	}

	// automatically set left_up and right down
	public void setRight_down(Point p1) {
		this.left_up = getMin(this.constant_corner, p1);
		this.right_down = getMax(this.constant_corner, p1);
	}

	public Point getRight_down() {
		return this.right_down;
	}

	public Point getLeft_up() {
		return this.left_up;
	}

	/**
	 * This method draw each shape
	 *
	 * @param g2d
	 */
	@Override
	//need modify
	public void draw(GraphicsContext g2d) {
		if (this.isSolid()) {
			g2d.setFill(this.getColor());
			g2d.fillRect(this.getLeft_up().getX(), this.getLeft_up().getY(),
					-(this.getLeft_up().getX() - this.getRight_down().getX()), -(this.getLeft_up().getY() - this.getRight_down().getY()));
		} else {
			g2d.setStroke(this.getColor());
			g2d.setLineWidth(this.getThickness());
			g2d.strokeRect(this.getLeft_up().getX(), this.getLeft_up().getY(),
					-(this.getLeft_up().getX() - this.getRight_down().getX()), -(this.getLeft_up().getY() - this.getRight_down().getY()));
		}

	}
}
