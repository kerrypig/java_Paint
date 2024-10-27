package ca.utoronto.utm.assignment2.paint;

import javafx.scene.input.MouseEvent;

public class RectangleStrategy implements ShapeStrategy {
    Rectangle rectangle;
    @Override
    public void mousePressed(PaintModel model, MouseEvent event) {
        System.out.println("Started Rectangle");
        Point corner1 = new Point(event.getX(), event.getY());
        this.rectangle = new Rectangle(corner1, corner1, model.geIsSolid(), model.getThickness(), model.getCurrentColor());
    }

    @Override
    public void mouseDragged(PaintModel model, MouseEvent event) {
        Point corner2 = new Point(event.getX(), event.getY());
        this.rectangle.setRight_down(corner2);
        model.addShape(this.rectangle);

    }

    @Override
    public void mouseMoved(PaintModel model, MouseEvent event) {

    }

    @Override
    public void mouseReleased(PaintModel model, MouseEvent event) {
        					if (this.rectangle != null) {
						Point corner2 = new Point(event.getX(), event.getY());
						this.rectangle.setRight_down(corner2);
						model.addShape(this.rectangle);
						this.rectangle = null;
					}
    }
}
