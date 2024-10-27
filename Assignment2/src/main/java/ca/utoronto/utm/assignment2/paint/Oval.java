package ca.utoronto.utm.assignment2.paint;

public class Oval extends Rectangle {
    public Oval(Point p1, Point p2) {
        super(p1, p2);
    }
    public double getWidth(){
        return this.getRight_down().x-this.getLeft_up().x;
    }
    public double getHeight(){
        return this.getRight_down().y-this.getLeft_up().y;
    }

}
