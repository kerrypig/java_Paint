package ca.utoronto.utm.assignment2.paint;

import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class SelectionBoxStrategy implements ShapeStrategy{
    private ArrayList <SelectionBox> box;

    Shape currShape=new Rectangle(new Point(0,0),new Point(0,0),false,0, Color.BLACK);
    ArrayList<Shape> currShapes=new ArrayList<>();

    private double startX, startY;   // 记录鼠标按下时的位置
    private Rectangle rectangle;

    boolean startMove = false;


    @Override
    public void mousePressed(PaintModel model, MouseEvent event) {

        if (startMove) {
            startX = event.getX();
            startY = event.getY();

        }else {

            currShapes.add(currShape);
            this.box = new ArrayList<>();


            System.out.println("Started SelectionBox");
            Point corner1 = new Point(event.getX(), event.getY());
            this.rectangle = new Rectangle(corner1, corner1, false, 1, Color.BLUE);
            this.rectangle.setDashed(true);

//        ArrayList<Shape> shapes = model.getShapes();
//        for (Shape shape : shapes) {
//            if (shape.contains(event.getX(),event.getY())){
//                currShapes.add(shape);
//            }
//        }
//        for (Shape shape : currShapes) {
//            this.box.add(new SelectionBox(shape));
//        }

//        model.addShape(this.box);
        }

    }

    @Override
    public void mouseDragged(PaintModel model, MouseEvent event) {
        if (startMove) {
            model.getPaintPanel().setCursor(Cursor.MOVE);

            System.out.println("move shape");
            for (Shape shape : currShapes) {
                shape.move(event.getX()-startX,event.getY()-startY);
                model.addShape(shape);
            }

            startX = event.getX();
            startY = event.getY();
        }else {


            Point corner2 = new Point(event.getX(), event.getY());
            this.rectangle.setRight_down(corner2);
            model.addShape(this.rectangle);
        }
    }

    @Override
    public void mouseMoved(PaintModel model, MouseEvent event) {


    }

    @Override
    public void mouseReleased(PaintModel model, MouseEvent event) {
        if(!startMove) {
            ArrayList<Shape> shapes = model.getShapes();
            for (Shape shape : shapes) {
                if (!(shape instanceof SelectionBox)) {
                    for (int i = (int) this.startX; i < event.getX(); i++) {
                        for (int j = (int) this.startY; j < event.getY(); j++) {
                            if (shape.contains(i, j) && !currShapes.contains(shape)) {
                                currShapes.add(shape);
                                System.out.println("add one");
                            }
                        }
                    }
                }

            }
            model.addFinalShape(this.rectangle);
            model.removeShape(this.rectangle);
            for (Shape shape : currShapes) {
                this.box.add(new SelectionBox(shape));
            }
            for (SelectionBox box : box) {
                model.addShape(box);
            }
            model.removeShape(model.getShapes().getLast());
            startMove = true;
        }else{
            for (Shape shape : currShapes) {
                shape.move(event.getX()-startX,event.getY()-startY);
                model.addFinalShape(shape);
            }
            model.removeShape(this.rectangle);
            model.getPaintPanel().setCursor(Cursor.DEFAULT);

        }
    }

    @Override
    public void mouseClicked(PaintModel model, MouseEvent event) {
//        if (startMove&&!this.rectangle.contains(event.getX(), event.getY())) {
//            this.box.clear();
//            startMove = false;
//        }

    }
}
