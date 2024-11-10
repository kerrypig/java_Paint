package ca.utoronto.utm.assignment2.paint;

import javafx.scene.input.MouseEvent;

public class CircleStrategy implements ShapeStrategy {
	private Circle circle;

	@Override
	public void mousePressed(PaintModel model, MouseEvent event) {
		System.out.println("Started Circle");
		Point centre = new Point(event.getX(), event.getY());
		this.circle = new Circle(centre, 0, model.geIsSolid(), model.getThickness(), model.getCurrentColor());

	}

	@Override
	public void mouseDragged(PaintModel model, MouseEvent event) {
		if (this.circle != null) {
			findCircle(model, event,false);
		}
	}

	@Override
	public void mouseMoved(PaintModel model, MouseEvent event) {

	}

	@Override
	public void mouseReleased(PaintModel model, MouseEvent event) {
		if (this.circle != null) {
			findCircle(model, event, true);
			System.out.println("Added Circle");
			this.circle = null;
		}

	}

	@Override
	public void mouseClicked(PaintModel model, MouseEvent event) {

	}

	private void findCircle(PaintModel model, MouseEvent mouseEvent, boolean isFinal) {
		double newX = mouseEvent.getX();
		double newY = mouseEvent.getY();
		double oldx = this.circle.getCentre().getX();
		double oldy = this.circle.getCentre().getY();
		double NewRadius = Math.sqrt(Math.pow(newX - oldx, 2) + Math.pow(newY - oldy, 2));
		this.circle.setRadius(NewRadius);
		if (isFinal) {
			model.addFinalShape(this.circle);
		}else{
		model.addShape(this.circle);}
	}
}
