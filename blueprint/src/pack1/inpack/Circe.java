package pack1.inpack;

import java.math.*;

public class Circe {
    private double radius;

    public Circe(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return radius*radius*Math.PI;
    }

    public double getRadius() {
        return radius;
    }
}