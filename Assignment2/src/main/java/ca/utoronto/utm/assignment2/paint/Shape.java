package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape {
	private boolean isSolid;
	private double thickness = 1.0;
	private Color color;
	private Point center;
	private int length_x, length_y;

	public Shape(boolean isSolid, double thickness, Color color, Point center, int length_x, int length_y) {
		this.isSolid = isSolid;
		this.thickness = thickness;
		this.color = color;
		this.center = center;
		this.length_x = length_x;
		this.length_y = length_y;
	}

	public double getThickness() {
		return this.thickness;
	}

	public void setThickness(double thickness) {
		this.thickness = thickness;
	}

	public boolean isSolid() {
		return this.isSolid;
	}

	public void setSolid(boolean isSolid) {
		this.isSolid = isSolid;
	}

	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	public Point getCenter() {
		return this.center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}

	public int getLength_x() {
		return this.length_x;
	}
	public void setLength_x(int length_x) {
		this.length_x = length_x;
	}
	public int getLength_y() {
		return this.length_y;
	}

	public void setLength_y(int length_y) {
		this.length_y = length_y;
	}

	public boolean contains(double x, double y) {
		if ((this.center.getX()+this.length_x>=x)&&(x>=this.center.getX())
		&& (this.center.getY()+this.length_y>=y)&&(y>=this.center.getY())) {
			return true;
		}
		return false;
	}

	public void move(double x, double y) {

	}


	/**
	 * This method draw each shape, NEED TO OVERRIDE THIS!
	 *
	 * @param g2d
	 */
	public void draw(GraphicsContext g2d) {
	}
}
