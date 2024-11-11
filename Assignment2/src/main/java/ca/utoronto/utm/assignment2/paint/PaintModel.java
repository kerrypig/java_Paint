package ca.utoronto.utm.assignment2.paint;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
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

	public void removeShape(Shape s) {
		this.shapes.remove(s);
		this.setChanged();
		this.notifyObservers();
	}

	// method for undo
	public void removeLastShape() {
		if (!shapes.isEmpty()) {
			shapes.remove(shapes.size() - 1);
			setChanged();
			notifyObservers();
		}
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void clearShapes() {
		shapes.clear();
		setChanged();
		notifyObservers();
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
