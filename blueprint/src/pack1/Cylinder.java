package pack1;

public class Cylinder {
    Circle circle;
    double height;

    public Cylinder(Circle circle, double height) {
        setCircle(circle);
        setHeight(height);
    }

    public Cylinder() {
    }

    public void setCircle(Circle c) {
        this.circle = c;
    }

    public void setHeight(double h) {
        this.height = h;
    }

    public double getVolume() {
        return circle.getArea()*height;
    }

}