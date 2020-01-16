package ir.ac.kntu.model;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Motorcycle extends Vehicle {
    public Motorcycle(double width, double length, double maxV, double v, double aP, double aN, double angle, double centerX, double centerY, Path nowPath, Rectangle shape) {
        super(width, length, maxV, v, aP, aN, angle, centerX, centerY, nowPath, shape);
    }
}
