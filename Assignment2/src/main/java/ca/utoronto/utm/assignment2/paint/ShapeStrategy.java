package ca.utoronto.utm.assignment2.paint;

import javafx.scene.input.MouseEvent;

public interface ShapeStrategy {
    void mousePressed(PaintModel model, MouseEvent event);
    void mouseDragged(PaintModel model, MouseEvent event);
    void mouseMoved(PaintModel model, MouseEvent event);
    void mouseReleased(PaintModel model, MouseEvent event);
}
