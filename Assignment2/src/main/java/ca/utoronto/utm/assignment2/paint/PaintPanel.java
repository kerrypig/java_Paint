package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.Canvas;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PaintPanel extends Canvas implements EventHandler<MouseEvent>, Observer {
	private String mode = "Circle";
	private PaintModel model;

	private Circle circle; // This is VERY UGLY, should somehow fix this!!
	private Rectangle rectangle;
	private Squiggle squiggle;
	private Oval oval;

	public PaintPanel(PaintModel model) {
		super(300, 300);
		this.model = model;
		this.model.addObserver(this);

		this.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
		this.addEventHandler(MouseEvent.MOUSE_RELEASED, this);
		this.addEventHandler(MouseEvent.MOUSE_MOVED, this);
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		this.addEventHandler(MouseEvent.MOUSE_DRAGGED, this);
	}

	/**
	 * Controller aspect of this
	 */
	public void setMode(String mode) {
		this.mode = mode;
		System.out.println(this.mode);
	}

	@Override
	public void handle(MouseEvent mouseEvent) {
		// Later when we learn about inner classes...
		// https://docs.oracle.com/javafx/2/events/DraggablePanelsExample.java.htm

		EventType<MouseEvent> mouseEventType = (EventType<MouseEvent>) mouseEvent.getEventType();

		// "Circle", "Rectangle", "Square", "Squiggle", "Polyline"
		switch (this.mode) {
			case "Circle":
				if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
					System.out.println("Started Circle");
					Point centre = new Point(mouseEvent.getX(), mouseEvent.getY());
					this.circle = new Circle(centre, 0, model.geIsSolid(), model.getThickness(), model.getCurrentColor());
				} else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
					if (this.circle != null) {
						findCircle(mouseEvent);
					}

				} else if (mouseEventType.equals(MouseEvent.MOUSE_MOVED)) {

				} else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
					if (this.circle != null) {
						findCircle(mouseEvent);
						System.out.println("Added Circle");
						this.circle = null;
					}
				}

				break;
			case "Rectangle":
				if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
					// first corner
					System.out.println("Started Rectangle");
					Point corner1 = new Point(mouseEvent.getX(), mouseEvent.getY());
					this.rectangle = new Rectangle(corner1, corner1, model.geIsSolid(), model.getThickness(), model.getCurrentColor());
				} else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
					Point corner2 = new Point(mouseEvent.getX(), mouseEvent.getY());
					this.rectangle.setRight_down(corner2);
					this.model.addShape(this.rectangle);
				} else if (mouseEventType.equals(MouseEvent.MOUSE_MOVED)) {

				} else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
					if (this.rectangle != null) {
						Point corner2 = new Point(mouseEvent.getX(), mouseEvent.getY());
						this.rectangle.setRight_down(corner2);
						this.model.addShape(this.rectangle);
						this.rectangle = null;
					}
				}
				break;
			case "Square":
				break;
			case "Squiggle":
				if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
					System.out.println("Started Squiggle");
					this.squiggle = new Squiggle(model.geIsSolid(), model.getThickness(), model.getCurrentColor());
					squiggle.addPoint(new Point(mouseEvent.getX(), mouseEvent.getY()));
				} else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
					squiggle.addPoint(new Point(mouseEvent.getX(), mouseEvent.getY()));
					if (this.squiggle != null) {
						this.model.addShape(this.squiggle);
					}

				} else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
					if (this.squiggle != null) {
						this.model.addShape(this.squiggle);
						System.out.println("Added Squiggle");
						this.squiggle = null;
					}
				}
				break;
			case "Polyline":
				break;
			case "Oval":
				if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
					// first corner
					System.out.println("Started Oval");
					Point corner1 = new Point(mouseEvent.getX(), mouseEvent.getY());
					this.oval = new Oval(corner1, corner1, model.geIsSolid(), model.getThickness(), model.getCurrentColor());
				} else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
					Point corner2 = new Point(mouseEvent.getX(), mouseEvent.getY());
					this.oval.setRight_down(corner2);
					this.model.addShape(this.oval);
				} else if (mouseEventType.equals(MouseEvent.MOUSE_MOVED)) {

				} else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
					if (this.oval != null) {
						Point corner2 = new Point(mouseEvent.getX(), mouseEvent.getY());
						this.oval.setRight_down(corner2);
						this.model.addShape(this.oval);
						this.oval = null;
					}
				}
			default:
				break;
		}
	}

	/**
	 * This method finds the current circle.
	 *
	 * @param mouseEvent
	 */
	private void findCircle(MouseEvent mouseEvent) {
		double newX = mouseEvent.getX();
		double newY = mouseEvent.getY();
		double oldx = this.circle.getCentre().getX();
		double oldy = this.circle.getCentre().getY();
		double NewRadius = Math.sqrt(Math.pow(newX - oldx, 2) + Math.pow(newY - oldy, 2));
		this.circle.setRadius(NewRadius);
		this.model.addShape(this.circle);
	}


	@Override
	public void update(Observable o, Object arg) {
		// Clear
		GraphicsContext g2d = this.getGraphicsContext2D();
		g2d.clearRect(0, 0, this.getWidth(), this.getHeight());

		// Draw shapes
		ArrayList<Shape> shapes = this.model.getShapes();
		for (Shape s : shapes) {
			s.draw(g2d);
		}

		// Draw Squiggles
//		ArrayList<Squiggle> squiggles = this.model.getSquiggles();
//		g2d.setFill(Color.YELLOW);
//		for (Squiggle s : this.model.getSquiggles()) {
//			ArrayList<Point> squigglePoints = s.getPoints();
//			paintPoints(g2d, squigglePoints);
//		}

	}
}


