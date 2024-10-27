package ca.utoronto.utm.assignment2.paint;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
	//
	private boolean isSolid = true;
	private float thickness = 1.0f;
	private Color currentColor = Color.BLACK;

	private ArrayList<Shape> shapes = new ArrayList<>();

	private ArrayList<Point> points = new ArrayList<Point>();
	private ArrayList<Squiggle> squiggles = new ArrayList<Squiggle>();


	//
	public boolean geIsSolid() {
		return isSolid;
	}

	//
	public void setIsSolid(boolean isSolid) {
		this.isSolid = isSolid;
		setChanged();
		notifyObservers();

	}

	public float getThickness() {
		return thickness;
	}

	public void setThickness(float thickness) {
		this.thickness = thickness;
		setChanged();
		notifyObservers();
	}


	public void addPoint(Point p) {
		this.points.add(p);
		this.setChanged();
		this.notifyObservers();
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void addShape(Shape s) {
		this.shapes.add(s);
		this.setChanged();
		this.notifyObservers();
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	//TODO: DELETE
	public void addSquiggle(Squiggle squiggle) {
		this.squiggles.add(squiggle);
		this.setChanged();
		this.notifyObservers();
	}

	public ArrayList<Squiggle> getSquiggles() {
		return squiggles;
	}

	public void setCurrentColor(Color color) {
		this.currentColor = color;
	}

	public Color getCurrentColor() {
		return currentColor;
	}
}
