package ca.utoronto.utm.assignment2.paint;

public class Point {
	private double x, y; // Available to our package

	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
