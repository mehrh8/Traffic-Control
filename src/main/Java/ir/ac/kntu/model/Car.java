package ir.ac.kntu.model;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Car extends Vehicle implements Runnable{
    private double xt=0,yt=0;
    public Car(double maxV, double v, double aP, double aN, Location location, Path nowPath, Rectangle shape) {
        super(20, 30, maxV, v, aP, aN, location, nowPath, shape);
        updateShape();
    }

    @Override
    public void run() {
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //centerX-=v/1000;
                Location newLocation=nowPath.getNextLocation(location,v/1000);
                if (newLocation!=null) location=newLocation;
                else nowPath=nowPath.getNext();
            }
        },0,1);
        AnimationTimer animationTimer=new AnimationTimer() {
            @Override
            public void handle(long l) {
                updateShape();
            }
        };
        animationTimer.start();
    }
}