package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends Shape{

    private Point left_up, right_down, constant_corner;

    public Square(Point p1, Point p2, boolean isSolid, double thickness, Color color) {
        //p1: corner 1, p2: corner 2
        super(isSolid, thickness, color);
        this.left_up = p1;
        this.right_down = p2;
        this.constant_corner = p1;
    }


    private Point getMax(Point num1, Point num2) {
        double x1 = num1.getX();
        double y1 = num1.getY();
        double x2 = num2.getX();
        double y2 = num2.getY();
        Point out = new Point(Math.max(x1, x2), Math.max(y1, y2));
        return out;
    }

    private Point getMin(Point num1, Point num2) {
        double x1 = num1.getX();
        double y1 = num1.getY();
        double x2 = num2.getX();
        double y2 = num2.getY();
        Point out = new Point(Math.min(x1, x2), Math.min(y1, y2));
        return out;
    }

    public void setLeft_up(Point p1) {
        this.left_up = p1;
    }


    public void setRight_down(Point p1) {
        double width = Math.abs(p1.getX() - constant_corner.getX());
        double height = Math.abs(p1.getY() - constant_corner.getY());

        double sideLength = Math.max(width, height);

        // corner is top-left
        if (constant_corner.getX() <= p1.getX() && constant_corner.getY() <= p1.getY()) {
            this.left_up = constant_corner;
            this.right_down = new Point(constant_corner.getX() + sideLength, constant_corner.getY() + sideLength);
        }
        // corner is bottom-right
        else if (constant_corner.getX() >= p1.getX() && constant_corner.getY() >= p1.getY()) {
            this.right_down = constant_corner;
            this.left_up = new Point(constant_corner.getX() - sideLength, constant_corner.getY() - sideLength);
        }
        // corner is bottom-left
        else if (constant_corner.getX() <= p1.getX() && constant_corner.getY() >= p1.getY()) {
            this.left_up = new Point(constant_corner.getX(), constant_corner.getY() - sideLength);
            this.right_down = new Point(constant_corner.getX() + sideLength, constant_corner.getY());
        }
        // corner is top-right
        else {
            this.left_up = new Point(constant_corner.getX() - sideLength, constant_corner.getY());
            this.right_down = new Point(constant_corner.getX(), constant_corner.getY() + sideLength);
        }
    }

    public Point getRight_down() {
        return this.right_down;
    }

    public Point getLeft_up() {
        return this.left_up;
    }

    /**
     * This method draw each shape
     * @param g2d
     */
    @Override
    //need modify
    public void draw(GraphicsContext g2d) {
        if (this.isSolid()) {
            g2d.setFill(this.getColor());
            g2d.fillRect(this.getLeft_up().getX(), this.getLeft_up().getY(),
                    -(this.getLeft_up().getX() - this.getRight_down().getX()), -(this.getLeft_up().getY() - this.getRight_down().getY()));
        } else {
            g2d.setStroke(this.getColor());
            g2d.setLineWidth(this.getThickness());
            g2d.strokeRect(this.getLeft_up().getX(), this.getLeft_up().getY(),
                    -(this.getLeft_up().getX() - this.getRight_down().getX()), -(this.getLeft_up().getY() - this.getRight_down().getY()));
        }

    }
}
