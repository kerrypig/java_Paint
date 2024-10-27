package ca.utoronto.utm.assignment2.paint;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
        //
        private boolean isSolid = true;
        private float thickness = 1.0f;

        private ArrayList<Shape> shapes = new ArrayList<>();

        private ArrayList<Point> points=new ArrayList<Point>();
//        private ArrayList<Circle> circles=new ArrayList<Circle>();
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

        public float getThickness(){
                return thickness;
        }
        public void setThickness(float thickness){
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

        public void addShape(Shape s){
                this.shapes.add(s);
                this.setChanged();
                this.notifyObservers();
        }
        public ArrayList<Shape> getShapes(){
                return shapes;
        }

        public void addSquiggle(Squiggle squiggle) {
                this.squiggles.add(squiggle);
                this.setChanged();
                this.notifyObservers();
        }

        public ArrayList<Squiggle> getSquiggles() {
                return squiggles;
        }

}
