package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Oval extends Rectangle {
	public Oval(Point p1, Point p2, boolean isSolid, double thickness, Color color) {
		super(p1, p2, isSolid, thickness, color);
	}

	public double getWidth() {
		return this.getRight_down().getX() - this.getLeft_up().getX();
	}

	public double getHeight() {
		return this.getRight_down().getY() - this.getLeft_up().getY();
	}

	/**
	 * This method draw each shape
	 * @param g2d
	 */
	@Override
	public void draw(GraphicsContext g2d) {
		g2d.setFill(Color.PURPLE);
		if (this.isSolid()) {
			g2d.fillOval(this.getLeft_up().getX(), this.getLeft_up().getY(), this.getWidth(), this.getHeight());
		} else {
			g2d.setLineWidth(this.getThickness());
			g2d.strokeOval(this.getLeft_up().getX(), this.getLeft_up().getY(), this.getWidth(), this.getHeight());
		}
	}
}
