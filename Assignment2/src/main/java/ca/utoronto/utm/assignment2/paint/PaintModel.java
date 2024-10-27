package ca.utoronto.utm.assignment2.paint;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
        //
        private boolean isSolid = true;
        private double thickness = 1.0;

        private ArrayList<Point> points=new ArrayList<Point>();
        private ArrayList<Circle> circles=new ArrayList<Circle>();
        private ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
        private ArrayList<Squiggle> squiggles = new ArrayList<Squiggle>();
        private ArrayList<Oval> ovals = new ArrayList<>();


        //
        public boolean geIsSolid() {
                return isSolid;
        }

        //
        public void setIsSolid(boolean isSolid){
                this.isSolid = isSolid;
                setChanged();
                notifyObservers();

        }

        public double getThickness(){
                return thickness;
        }
        public void setThickness(double thickness){
                this.thickness = thickness;
                setChanged();
                notifyObservers();
        }


        public void addPoint(Point p){
                this.points.add(p);
                this.setChanged();
                this.notifyObservers();
        }
        public ArrayList<Point> getPoints(){
                return points;
        }

        public void addCircle(Circle c){
                this.circles.add(c);
                this.setChanged();
                this.notifyObservers();
        }

        public ArrayList<Circle> getCircles(){
                return circles;
        }

        public void addRectangle(Rectangle r){
                this.rectangles.add(r);
                this.setChanged();
                this.notifyObservers();
        }
        public ArrayList<Rectangle> getRectangles(){return rectangles;}

        public void addSquiggle(Squiggle squiggle) {
                this.squiggles.add(squiggle);
                this.setChanged();
                this.notifyObservers();
        }

        public ArrayList<Squiggle> getSquiggles() {
                return squiggles;
        }

        public void addOval(Oval o){
                this.ovals.add(o);
                this.setChanged();
                this.notifyObservers();
        }
        public ArrayList<Oval> getOvals() {
                return ovals;
        }
}
