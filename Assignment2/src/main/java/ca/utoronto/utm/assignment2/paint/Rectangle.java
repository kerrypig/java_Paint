package ca.utoronto.utm.assignment2.paint;

public class Rectangle {
    private Point left_up,right_down;
    public Rectangle(Point p1, Point p2) {
        //p1: corner 1, p2: corner 2
        this.left_up = p1;
        this.right_down = p2;
    }
    public void setLeft_up(Point p1) {
        this.left_up = p1;
    }
    public void setRight_down(Point p1) {
        this.right_down = p1;
    }
    public Point getRight_down() {
        return this.right_down;
    }
    public Point getLeft_up() {
        return this.left_up;
    }
}
