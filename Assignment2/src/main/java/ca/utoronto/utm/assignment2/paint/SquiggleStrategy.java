package ca.utoronto.utm.assignment2.paint;

import javafx.scene.input.MouseEvent;

public class SquiggleStrategy implements ShapeStrategy{
    Squiggle squiggle;
    @Override
    public void mousePressed(PaintModel model, MouseEvent event) {
        System.out.println("Started Squiggle");
        this.squiggle = new Squiggle(model.geIsSolid(), model.getThickness(), model.getCurrentColor());
        squiggle.addPoint(new Point(event.getX(), event.getY()));

    }
    @Override
    public void mouseDragged(PaintModel model, MouseEvent event) {
        squiggle.addPoint(new Point(event.getX(), event.getY()));
        if (this.squiggle != null) {
            model.addShape(this.squiggle);
        }
    }

    @Override
    public void mouseMoved(PaintModel model, MouseEvent event) {

    }

    @Override
    public void mouseReleased(PaintModel model, MouseEvent event) {
        if (this.squiggle != null) {
            model.addShape(this.squiggle);
            System.out.println("Added Squiggle");
            this.squiggle = null;
        }
    }
}
