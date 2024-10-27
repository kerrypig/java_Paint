package ca.utoronto.utm.assignment2.paint;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

public class Circle extends Shape {
        private Point centre;
        private double radius;
        //


        public Circle(Point centre, int radius, boolean isSolid,double thickness) {
                super(isSolid,thickness);
                this.centre = centre;
                this.radius = radius;
                //

        }

        public Point getCentre() {
                return centre;
        }

        public void setCentre(Point centre) {
                this.centre = centre;
        }

        public double getRadius() {
                return radius;
        }

        public void setRadius(double radius) {
                this.radius = radius;
        }

        //
        @Override
        public void draw(GraphicsContext g2d) {
                g2d.setFill(Color.GREEN);
                double x = this.getCentre().x;
                double y = this.getCentre().y;
                double radius = this.getRadius();
                if (this.isSolid()) {
                        g2d.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
                } else {
                        g2d.setLineWidth(this.getThickness());
                        g2d.strokeOval(x - radius, y - radius, 2 * radius, 2 * radius);
                }
        }


}
