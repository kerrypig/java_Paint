package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Squiggle extends Shape {
	private ArrayList<Point> points;
	private int max_x,max_y,min_x,min_y;


	public Squiggle(boolean isSolid, double thickness, Color color) {

		super(isSolid, thickness, color,new Point(0,0),0,0);
		this.max_x=0;
		this.max_y=0;
		this.min_x=10000;
		this.min_y=10000;
		this.points = new ArrayList<>();
	}

	public void addPoint(Point point) {
		this.points.add(point);
		for (Point p : points){
			this.max_x= (int) Math.max(this.max_x,p.getX());
			this.max_y= (int) Math.max(this.max_y,p.getY());
			this.min_x= (int) Math.min(this.min_x,p.getX());
			this.min_y= (int) Math.min(this.min_y,p.getY());
		}
		super.setCenter(new Point(this.min_x,this.min_y));
		super.setLength_x((int)Math.abs(this.min_x-this.max_x));
		super.setLength_y((int)Math.abs(this.min_y-this.max_y));

	}
	public void move(double x, double y) {
		ArrayList<Point> newPoints = new ArrayList<>();
		for (Point p : this.points){
			newPoints.add( new Point(p.getX()+x,p.getY()+y));
		}
		this.points = newPoints;

		super.setCenter(new Point(this.getCenter().getX()+x,this.getCenter().getY()+y));
	}

	public ArrayList<Point> getPoints() {
		return this.points;
	}

	/**
	 * This method draw each shape
	 *
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