package ca.utoronto.utm.assignment2.paint;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;

public class EraserStrategy implements ShapeStrategy {
    @Override
    public void mousePressed(PaintModel model, MouseEvent event) {
        for (Shape shape : new ArrayList<>(model.getShapes())) {
            if (shape.contains(event.getX(), event.getY())) {
                model.removeShape(shape);
                break;
            }
        }
    }

    @Override
    public void mouseDragged(PaintModel model, MouseEvent event) {}

    @Override
    public void mouseMoved(PaintModel model, MouseEvent event) {}

    @Override
    public void mouseReleased(PaintModel model, MouseEvent event) {}

    @Override
    public void mouseClicked(PaintModel model, MouseEvent event) {}
}



