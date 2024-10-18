package ca.utoronto.utm.assignment2.paint;
import javafx.scene.canvas.Canvas;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PaintPanel extends Canvas implements EventHandler<MouseEvent>, Observer {
    private String mode="Circle";
    private PaintModel model;

    public Circle circle; // This is VERY UGLY, should somehow fix this!!
    private Rectangle rectangle;

    public PaintPanel(PaintModel model) {
        super(300, 300);
        this.model=model;
        this.model.addObserver(this);

        this.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        this.addEventHandler(MouseEvent.MOUSE_RELEASED, this);
        this.addEventHandler(MouseEvent.MOUSE_MOVED, this);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
        this.addEventHandler(MouseEvent.MOUSE_DRAGGED, this);
    }
    /**
     *  Controller aspect of this
     */
    public void setMode(String mode){
        this.mode=mode;
        System.out.println(this.mode);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        // Later when we learn about inner classes...
        // https://docs.oracle.com/javafx/2/events/DraggablePanelsExample.java.htm

        EventType<MouseEvent> mouseEventType = (EventType<MouseEvent>) mouseEvent.getEventType();

        // "Circle", "Rectangle", "Square", "Squiggle", "Polyline"
        switch(this.mode){
            case "Circle":
                if(mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    System.out.println("Started Circle");
                     Point centre = new Point(mouseEvent.getX(), mouseEvent.getY());
                        this.circle=new Circle(centre, 0);
                } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    if(this.circle!=null){
                    double newX=mouseEvent.getX();
                    double newY=mouseEvent.getY();
                    double oldx = this.circle.getCentre().x;
                    double oldy = this.circle.getCentre().y;
                    double NewRadius = Math.sqrt(Math.pow(newX - oldx,2) + Math.pow(newY - oldy,2));
                    this.circle.setRadius(NewRadius);
                    this.model.addCircle(this.circle);}

                } else if (mouseEventType.equals(MouseEvent.MOUSE_MOVED)) {

                } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    if(this.circle!=null){
                        double newX = mouseEvent.getX();
                        double newY = mouseEvent.getY();
                        double oldX = this.circle.getCentre().x;
                        double oldY = this.circle.getCentre().y;
                        double radius = Math.sqrt(Math.pow(newX - oldX, 2) + Math.pow(newY - oldY, 2));
                        this.circle.setRadius(radius);
                        this.model.addCircle(this.circle);
                        this.circle = null;
                        }
                }

                break;
            case "Rectangle":
                if(mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    // first corner
                    System.out.println("Started Rectangle");
                    Point corner1 = new Point(mouseEvent.getX(), mouseEvent.getY());
                    this.rectangle = new Rectangle(corner1,corner1);
                }else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    Point corner2 = new Point(mouseEvent.getX(), mouseEvent.getY());
                    this.rectangle.setRight_down(corner2);
                    this.model.addRectangle(this.rectangle);
                } else if (mouseEventType.equals(MouseEvent.MOUSE_MOVED)) {

                } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    if(this.rectangle!=null){
                        Point p2 = new Point(mouseEvent.getX(), mouseEvent.getY());
                        this.rectangle.setRight_down(p2);
                        this.model.addRectangle(this.rectangle);
                        this.rectangle = null;
                    }
                }
                break;
            case "Square": break;
            case "Squiggle":
                if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    this.model.addPoint(new Point(mouseEvent.getX(), mouseEvent.getY()));
                }
                break;
            case "Polyline": break;
            default: break;
        }
    }
    @Override
    public void update(Observable o, Object arg) {

                GraphicsContext g2d = this.getGraphicsContext2D();
                g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
                // Draw Lines
                ArrayList<Point> points = this.model.getPoints();

                g2d.setFill(Color.RED);
                for(int i=0;i<points.size()-1; i++){
                        Point p1=points.get(i);
                        Point p2=points.get(i+1);
                        g2d.strokeLine(p1.x,p1.y,p2.x,p2.y);
                }

                // Draw Circles
                ArrayList<Circle> circles = this.model.getCircles();
                // Draw Rectangle
                ArrayList<Rectangle> rectangles = this.model.getRectangles();

                g2d.setFill(Color.GREEN);
                for(Circle c: this.model.getCircles()){
                        double x = c.getCentre().x;
                        double y = c.getCentre().y;
                        double radius = c.getRadius();
                        g2d.fillOval(x, y, radius, radius);
                }

                g2d.setFill(Color.BLUE);
                for(Rectangle r: this.model.getRectangles()){
                    g2d.fillRect(r.getLeft_up().x,r.getLeft_up().y,
                            -(r.getLeft_up().x-r.getRight_down().x),-(r.getLeft_up().y-r.getRight_down().y));
                }
    }
}
