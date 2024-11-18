import pack1.inpack.Circle;
import java.math.*;

public class Sphere {
    private Circle c;

    public Sphere(Circle c) {
        this.c = c;
    }

    public double getVolume() {
        double r = c.getRadius();
        return r*r*r*(4/3)*Math.PI;
    }
}