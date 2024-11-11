package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape {
	private Point left_up, right_down, constant_corner;
	private boolean dash = false;


	public Rectangle(Point p1, Point p2, boolean isSolid, double thickness, Color color) {
		//p1: corner 1, p2: corner 2
		super(isSolid, thickness, color,new Point(0,0),0,0);
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
		super.setCenter(this.left_up );
		super.setLength_x((int) -(this.getLeft_up().getX() - this.getRight_down().getX()));
		super.setLength_y((int) -(this.getLeft_up().getY() - this.getRight_down().getY()));
	}

	public Point getRight_down() {
		return this.right_down;
	}

	public Point getLeft_up() {
		return this.left_up;
	}

	public void move(double x, double y) {
		this.left_up = new Point(this.left_up.getX() + x, this.left_up.getY() + y);
		this.right_down = new Point(this.right_down.getX() + x, this.right_down.getY() + y);
		super.setCenter(this.left_up );

	}
	public void setDashed(Boolean dashed) {
		this.dash = dashed;
	}

	/**
	 * This method draw each shape
	 *
	 * @param g2d
	 */
	@Override
	//need modify
	public void draw(GraphicsContext g2d) {
		if (this.dash) {
			g2d.setLineDashes(5,5);
		}else{
			g2d.setLineDashes();
		}
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
