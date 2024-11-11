package ca.utoronto.utm.assignment2.paint;

import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class SelectionBoxStrategy implements ShapeStrategy{
    private SelectionBox box;
    Shape currShape=new Rectangle(new Point(0,0),new Point(0,0),false,0, Color.BLACK);
    private double startX, startY;   // 记录鼠标按下时的位置


    @Override
    public void mousePressed(PaintModel model, MouseEvent event) {
        ArrayList<Shape> shapes = model.getShapes();
        for (Shape shape : shapes) {
            if (shape.contains(event.getX(),event.getY())){
                this.currShape = shape;
            }
        }
        this.box = new SelectionBox(this.currShape);
        model.addShape(this.box);
        model.getThisPanel().setCursor(Cursor.MOVE);
        startX = event.getX();
        startY = event.getY();

    }

    @Override
    public void mouseDragged(PaintModel model, MouseEvent event) {
        if (currShape.contains(event.getX(),event.getY())){

            System.out.println("move shape");
        this.currShape.move(event.getX()-startX,event.getY()-startY);
        model.addShape(this.currShape);

        startX = event.getX();
        startY = event.getY();}
    }

    @Override
    public void mouseMoved(PaintModel model, MouseEvent event) {

    }

    @Override
    public void mouseReleased(PaintModel model, MouseEvent event) {
        model.addFinalShape(this.currShape);
        model.removeShape(this.currShape);
        model.getThisPanel().setCursor(Cursor.DEFAULT);

    }

    @Override
    public void mouseClicked(PaintModel model, MouseEvent event) {

    }
}
