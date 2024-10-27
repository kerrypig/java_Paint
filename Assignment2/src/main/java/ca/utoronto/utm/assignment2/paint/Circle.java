package ca.utoronto.utm.assignment2.paint;


public class Circle {
        private Point centre;
        private double radius;
        //
        private boolean isSolid;

        public Circle(Point centre, int radius, boolean isSolid) {
                this.centre = centre;
                this.radius = radius;
                //
                this.isSolid = isSolid;
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
        public boolean getIsSolid() {
                return isSolid;
        }

}
