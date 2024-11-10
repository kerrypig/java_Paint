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

	private int finalShapeNum = 0;
	private ArrayList<Shape> undoShape = new ArrayList<>();


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

	public void addFinalShape(Shape s) {
		if (finalShapeNum == 0){
			this.shapes.clear();
			this.shapes.add(s);
			finalShapeNum++;
			this.setChanged();
			this.notifyObservers();
		}else{
			this.shapes= new ArrayList<>(this.shapes.subList(0,this.finalShapeNum));
			this.shapes.add(s);
			finalShapeNum++;
			this.setChanged();
			this.notifyObservers();
		}
	}

	public void removeShape(Shape s) {
		this.shapes.remove(s);
		this.setChanged();
		this.notifyObservers();
	}

	// method for undo
	public void removeLastShape() {
		if (!shapes.isEmpty()) {
			undoShape.add(shapes.remove(shapes.size() - 1));
			finalShapeNum--;
			setChanged();
			notifyObservers();
		}
	}
	//method for redo
	public void addOneRemovedShape(){
		if (!undoShape.isEmpty()) {
			shapes.add(undoShape.remove(undoShape.size() - 1));
			finalShapeNum++;
			setChanged();
			notifyObservers();
		}
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	//TODO: DELETE THIS
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
