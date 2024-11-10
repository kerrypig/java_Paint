package ca.utoronto.utm.assignment2.paint;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class PolylineStrategy implements ShapeStrategy {
	private Polyline polyline;

	@Override
	public void mousePressed(PaintModel model, MouseEvent event) {

	}

	@Override
	public void mouseDragged(PaintModel model, MouseEvent event) {

	}

	@Override
	public void mouseMoved(PaintModel model, MouseEvent event) {
		if (this.polyline != null) {
			this.polyline.addPoint(new Point(event.getX(), event.getY()));
			model.addShape(this.polyline);
			this.polyline.getPoints().removeLast();
		}
	}

	@Override
	public void mouseReleased(PaintModel model, MouseEvent event) {

	}

	@Override
	public void mouseClicked(PaintModel model, MouseEvent event) {
		if (this.polyline == null && (event.getButton() == MouseButton.PRIMARY)) {
			System.out.println("Started Polyline");
			this.polyline = new Polyline(model.geIsSolid(), model.getThickness(), model.getCurrentColor());
			this.polyline.addPoint(new Point(event.getX(), event.getY()));
		} else if (event.getButton() == MouseButton.PRIMARY) { // Left-click for adding points
			Point clickedPoint = new Point((int) event.getX(), (int) event.getY());
			this.polyline.addPoint(clickedPoint);
			model.addShape(this.polyline);
		} else if (this.polyline != null && event.getButton() == MouseButton.SECONDARY) { // Right-click to finish polyline
			if (polyline.getPoints().size() == 1) {
				model.removeShape(this.polyline);
			}
			if (polyline.getPoints().size() > 1) {
				model.addFinalShape(this.polyline);// Add the polyline to the model permanently
				System.out.println("Added Polyline");
			}
			this.polyline = null;
			System.out.println("Canceled Polyline");
		}
	}
}
