package ca.utoronto.utm.assignment2.paint;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class TriangleStrategy implements ShapeStrategy {
	private Triangle triangle;
	private Polyline polyline;
	private ArrayList<Point> trianglePoints = new ArrayList<>();
	private boolean started = false;

	@Override
	public void mousePressed(PaintModel model, MouseEvent event) {


	}

	@Override
	public void mouseDragged(PaintModel model, MouseEvent event) {

	}

	@Override
	public void mouseMoved(PaintModel model, MouseEvent event) {
		if (this.started){
			if (trianglePoints.size() == 1) {
				this.polyline.addPoint(new Point(event.getX(), event.getY()));
				model.addShape(this.polyline);
				this.polyline.getPoints().removeLast();

			}
			if (trianglePoints.size() == 2) {
				this.polyline.addPoint(new Point(event.getX(), event.getY()));
				this.polyline.addPoint(trianglePoints.get(0));
				model.addShape(this.polyline);
				this.polyline.getPoints().removeLast();
				this.polyline.getPoints().removeLast();

			}
			if (trianglePoints.size() == 3) {

			}
		}

	}

	@Override
	public void mouseClicked(PaintModel model, MouseEvent event) {
		if (this.trianglePoints.isEmpty() && (event.getButton() == MouseButton.PRIMARY)) {
			System.out.println("Started Triangle");
			this.started = true;
			Point clickedPoint = new Point(event.getX(), event.getY());
			this.trianglePoints.add(clickedPoint);
			this.polyline = new Polyline(model.geIsSolid(), model.getThickness(), model.getCurrentColor());
			this.polyline.addPoint(clickedPoint);
		} else {
			this.polyline.addPoint(new Point(event.getX(), event.getY()));
			handleMouseClick(model, event);
		}
	}

	@Override
	public void mouseReleased(PaintModel model, MouseEvent event) {

	}

	private void handleMouseClick(PaintModel model, MouseEvent event) {
		// Store the clicked point
		trianglePoints.add(new Point(event.getX(), event.getY()));

		if (trianglePoints.size() == 3) {
			// Create the triangle
			this.polyline.reset();
			this.triangle = new Triangle(trianglePoints.get(0), trianglePoints.get(1), trianglePoints.get(2),
					model.geIsSolid(), model.getThickness(), model.getCurrentColor());
			model.addFinalShape(triangle);
			System.out.println("Added Triangle");
			trianglePoints.clear(); // Clear the temporary points list
		}
	}
}
