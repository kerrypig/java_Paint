package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.Canvas;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PaintPanel extends Canvas implements EventHandler<MouseEvent>, Observer {
	private String mode = "Circle";
	private PaintModel model;
	private ShapeStrategy currentStrategy;

	public PaintPanel(PaintModel model) {
		super(300, 600);
		this.model = model;
		this.model.addObserver(this);

		this.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
		this.addEventHandler(MouseEvent.MOUSE_RELEASED, this);
		this.addEventHandler(MouseEvent.MOUSE_MOVED, this);
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		this.addEventHandler(MouseEvent.MOUSE_DRAGGED, this);
	}

	public void setMode(String mode) {
		this.mode = mode;
		switch (mode) {
			case "Circle":
				currentStrategy = new CircleStrategy();
				break;
			case "Rectangle":
				currentStrategy = new RectangleStrategy();
				break;
			case "Square":
				currentStrategy = new SquareStrategy();
				break;
            case "Oval":
				currentStrategy = new OvalStrategy();
				break;
			case "Squiggle":
				currentStrategy = new SquiggleStrategy();
				break;
			case "Triangle":
				currentStrategy = new TriangleStrategy();
				break;
			case "Polyline":
				currentStrategy = new PolylineStrategy();
				break;
			case "SelectionBox":
				currentStrategy = new SelectionBoxStrategy();
				break;

		}
	}

	@Override
	public void handle(MouseEvent mouseEvent) {
		if (currentStrategy != null) {
			switch (mouseEvent.getEventType().getName()) {
				case "MOUSE_PRESSED":
					currentStrategy.mousePressed(model, mouseEvent);
					break;
				case "MOUSE_DRAGGED":
					currentStrategy.mouseDragged(model, mouseEvent);
					break;
				case "MOUSE_RELEASED":
					currentStrategy.mouseReleased(model, mouseEvent);
					break;
				case "MOUSE_MOVED":
					currentStrategy.mouseMoved(model, mouseEvent);
					break;
				case "MOUSE_CLICKED":
					currentStrategy.mouseClicked(model, mouseEvent);
					break;
			}
		}
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
		System.out.println(shapes.size());
//		for (Shape s : shapes) {
//			System.out.println(s);
//		}

	}
}


