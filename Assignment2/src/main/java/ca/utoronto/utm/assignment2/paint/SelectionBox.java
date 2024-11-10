package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SelectionBox extends Shape {
    private Shape shape;

    public SelectionBox(Shape shape) {
        super(false, 1, Color.BLUE,new Point(0,0),0,0);
        this.shape = shape;
    }

    @Override
    public void draw(GraphicsContext g2d) {
        g2d.setStroke(this.getColor());
        g2d.setLineWidth(this.getThickness());
        g2d.strokeRect( this.shape.getCenter().getX()-10, this.shape.getCenter().getY()-10, this.shape.getLength_x()+10, this.shape.getLength_y()+10);
    }
}
