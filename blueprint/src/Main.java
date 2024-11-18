import pack1.*;
import pack2.*;

public class Main {
    public static void main(String[] args) {
        double r = 2;
        Circle c = new Circle(r);
        Cylinder cy = new Cylinder(c, 3);
        Sphere sp = new Sphere(c);

        System.out.println("반지름이 " + r + "인 원의 넓이 " + c.getArea());
        System.out.println("반지름이 " + r + "높이가 3 인 원기둥의 부피 " + cy.getVolume());
        System.out.println("반지름이 " + r + " 인 구의 부피 " + sp.getVolume());
    }
}