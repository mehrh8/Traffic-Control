package ir.ac.kntu.model;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Motorcycle extends Vehicle {
    public Motorcycle(double width, double length, double maxV, double v, double aP, double aN, Location location, Path nowPath,double distanceInNowPath, Rectangle shape) {
        super(width, length, maxV, v, aP, aN, location, nowPath,distanceInNowPath, shape);
    }
}
