package ca.utoronto.utm.assignment2.paint;

import javafx.scene.input.MouseEvent;

public class OvalStrategy implements ShapeStrategy {
	private Oval oval;

	@Override
	public void mousePressed(PaintModel model, MouseEvent event) {
		// first corner
		System.out.println("Started Oval");
		Point corner1 = new Point(event.getX(), event.getY());
		this.oval = new Oval(corner1, corner1, model.geIsSolid(), model.getThickness(), model.getCurrentColor());


	}

	@Override
	public void mouseDragged(PaintModel model, MouseEvent event) {
		Point corner2 = new Point(event.getX(), event.getY());
		this.oval.setRight_down(corner2);
		model.addShape(this.oval);
	}

	@Override
	public void mouseMoved(PaintModel model, MouseEvent event) {

	}

	@Override
	public void mouseReleased(PaintModel model, MouseEvent event) {
		if (this.oval != null) {
			Point corner2 = new Point(event.getX(), event.getY());
			this.oval.setRight_down(corner2);
			model.addShape(this.oval);
			this.oval = null;
		}
	}

	@Override
	public void mouseClicked(PaintModel model, MouseEvent event) {

	}
}
