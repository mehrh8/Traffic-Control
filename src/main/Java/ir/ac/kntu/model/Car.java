package ir.ac.kntu.model;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Car extends Vehicle implements Runnable{
    private Vehicle thisObject=this;
    private double xt=0,yt=0;
    private int counterTimer=0;
    private boolean isFrontVehicle=false;
    public Car(double maxV, double v, double aP, double aN, Location location, Path nowPath,double distanceInNowPath, Rectangle shape) {
        super(20, 30, maxV, v, aP, aN, location, nowPath, distanceInNowPath,shape);
        updateShape();
    }

    @Override
    public void run() {
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                calcIsFrontVehicle();
                if (isFrontVehicle) updateV(false);
                else updateV(true);
                Location newLocation=nowPath.getNextLocation(location,v/100);
                if (newLocation!=null) location=newLocation;
                else {
                    nowPath.getVehicles().remove(thisObject);
                    nowPath=nowPath.getNext();
                    nowPath.getVehicles().add(thisObject);
                }
                distanceInNowPath=nowPath.calcDistance(location,nowPath.getStartLocation());
            }
        },0,10);
        AnimationTimer animationTimer=new AnimationTimer() {
            @Override
            public void handle(long l) {
                updateShape();
            }
        };
        animationTimer.start();
    }
    public void calcIsFrontVehicle(){
        counterTimer+=1;
        if (counterTimer==5){
            nowPath.sortVehicles();
            int nowpathVehiclesSize=nowPath.getVehicles().size();
            int index=nowPath.getIndexVehicle(thisObject);
            if (index==nowpathVehiclesSize-1){
                if(nowPath.getNext()!=null) {
                    if (nowPath.getNext().getVehicles().size() > 0) {
                        nowPath.getNext().sortVehicles();
                        if (nowPath.getNext().calcDistance(nowPath.getNext().getVehicles().get(0).location, nowPath.getNext().getStartLocation()) + nowPath.calcDistance(location, nowPath.getEndLocation()) < 150)
                            isFrontVehicle = true;
                        else isFrontVehicle = false;
                    } else isFrontVehicle =false;
                } else  isFrontVehicle=false;
            }
            else if(nowPath.calcDistance(nowPath.getVehicles().get(index).location,nowPath.getVehicles().get(index+1).location)<150) isFrontVehicle=true;
            else isFrontVehicle=false;
            counterTimer=0;
        }
    }
}