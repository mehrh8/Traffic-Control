package ir.ac.kntu.model;

import javafx.scene.shape.Shape;

public abstract class Vehicle {
    double width;
    double length;
    double maxV;
    double v;
    double aP;
    double aN;
    double angle;
    double centerX;
    double centerY;
    Path nowPath;
    Shape shape;
    public abstract void move();
}
