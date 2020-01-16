package ir.ac.kntu.model;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.util.Timer;
import java.util.TimerTask;

public class Car extends Vehicle {
    AnimationTimer A=new AnimationTimer(){
        @Override
        public void handle(long l) {
            centerX-=v/10;
            updateShape();
            centerX-=v/10;
            updateShape();
            this.stop();
        }
    };
    public Car(double maxV, double v, double aP, double aN, double angle, double centerX, double centerY, Path nowPath, Rectangle shape) {
        super(20, 30, maxV, v, aP, aN, angle, centerX, centerY, nowPath, shape);
    }
}
