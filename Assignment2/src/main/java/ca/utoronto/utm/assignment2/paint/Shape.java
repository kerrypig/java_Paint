package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape {
	private boolean isSolid;
	private double thickness = 1.0;
	private Color color;

	public Shape(boolean isSolid, double thickness, Color color) {
		this.isSolid = isSolid;
		this.thickness = thickness;
		this.color = color;
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

	/**
	 * This method draw each shape, NEED TO OVERRIDE THIS!
	 * @param g2d
	 */
	public void draw(GraphicsContext g2d) {
	}
}
