package ca.utoronto.utm.assignment2.paint;

import javafx.scene.input.MouseEvent;

public class SquareStrategy implements ShapeStrategy {
    Square square;
    @Override
    public void mousePressed(PaintModel model, MouseEvent event) {
        System.out.println("Started Square");
        Point corner1 = new Point(event.getX(), event.getY());
        this.square = new Square(corner1, corner1, model.geIsSolid(), model.getThickness(), model.getCurrentColor());
    }

    @Override
    public void mouseDragged(PaintModel model, MouseEvent event) {
        Point corner2 = new Point(event.getX(), event.getY());
        this.square.setRight_down(corner2);
        model.addShape(this.square);

    }

    @Override
    public void mouseMoved(PaintModel model, MouseEvent event) {

    }

    @Override
    public void mouseClicked(PaintModel model, MouseEvent event) {

    }

    @Override
    public void mouseReleased(PaintModel model, MouseEvent event) {
        if (this.square != null) {
            Point corner2 = new Point(event.getX(), event.getY());
            this.square.setRight_down(corner2);
            model.addShape(this.square);
            this.square = null;
        }
    }
}