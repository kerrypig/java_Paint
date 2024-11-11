package ca.utoronto.utm.assignment2.paint;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape {
	private Point centre;
	private double radius;
	//


	public Circle(Point centre, int radius, boolean isSolid, double thickness, Color color) {
		super(isSolid, thickness, color,new Point(0,0),0,0);
		this.centre = centre;
		this.radius = radius;
	}

	public Point getCentre() {
		return centre;
	}

	public void setCentre(Point centre) {
		this.centre = centre;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {

		this.radius = radius;
		super.setCenter(new Point(this.centre.getX()-radius, this.centre.getY()-radius));
		super.setLength_x((int) (2*radius));
		super.setLength_y((int) (2*radius));
	}
	public void move(double x, double y) {
		this.centre = new Point(this.centre.getX()+x, this.centre.getY()+y);
		super.setCenter(new Point(this.centre.getX()-radius, this.centre.getY()-radius));

	}

	/**
	 * This method draw each shape
	 *
	 * @param g2d
	 */
	@Override
	public void draw(GraphicsContext g2d) {
		double x = this.getCentre().getX();
		double y = this.getCentre().getY();
		double radius = this.getRadius();
		if (this.isSolid()) {
			g2d.setFill(this.getColor());
			g2d.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
		} else {
			g2d.setStroke(this.getColor());
			g2d.setLineWidth(this.getThickness());
			g2d.strokeOval(x - radius, y - radius, 2 * radius, 2 * radius);
		}
	}


}
