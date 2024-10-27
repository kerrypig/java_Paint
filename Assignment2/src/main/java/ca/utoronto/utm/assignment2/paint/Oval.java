package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

public class Oval extends Rectangle {
    public Oval(Point p1, Point p2,boolean isSolid,double thickness) {
        super(p1, p2, isSolid,thickness);
    }
    public double getWidth(){
        return this.getRight_down().x-this.getLeft_up().x;
    }
    public double getHeight(){
        return this.getRight_down().y-this.getLeft_up().y;
    }
    public void draw(GraphicsContext g2d) {
        g2d.setFill(Color.PURPLE);
        if (this.isSolid()){
            g2d.fillOval(this.getLeft_up().x, this.getLeft_up().y,this.getWidth(), this.getHeight());
        }else{
            g2d.setLineWidth(this.getThickness());
            g2d.strokeOval(this.getLeft_up().x, this.getLeft_up().y,this.getWidth(), this.getHeight());
        }
    }
}
