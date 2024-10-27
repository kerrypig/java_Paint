package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public abstract class Shape {
    private boolean isSolid;
    private double thickness;
    private Color color;

    public Shape(boolean isSolid, double thickness) {
        this.isSolid = isSolid;
        this.thickness = thickness;
    }

    public double getThickness() {
        return thickness;
    }
    public void setThickness(double thickness) {
        this.thickness = thickness;
    }
    public boolean isSolid() {
        return isSolid;
    }
    public void setSolid(boolean isSolid) {
        this.isSolid = isSolid;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public void draw(GraphicsContext g) {}
}
