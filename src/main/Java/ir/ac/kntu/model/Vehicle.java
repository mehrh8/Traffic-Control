package ir.ac.kntu.model;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public abstract class Vehicle {
    double width;
    double length;
    double maxV;
    double v;
    double aP;
    double aN;
    Location location;
    Path nowPath;
    Rectangle shape;
    double distanceInNowPath;

    public double getDistanceInNowPath() {
        return distanceInNowPath;
    }

    public void setDistanceInNowPath(double distanceInNowPath) {
        this.distanceInNowPath = distanceInNowPath;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getMaxV() {
        return maxV;
    }

    public void setMaxV(double maxV) {
        this.maxV = maxV;
    }

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

    public double getaP() {
        return aP;
    }

    public void setaP(double aP) {
        this.aP = aP;
    }

    public double getaN() {
        return aN;
    }

    public void setaN(double aN) {
        this.aN = aN;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Path getNowPath() {
        return nowPath;
    }

    public void setNowPath(Path nowPath) {
        this.nowPath = nowPath;
    }

    public Rectangle getShape() {
        return shape;
    }

    public void setShape(Rectangle shape) {
        this.shape = shape;
    }

    public Vehicle(double width, double length, double maxV, double v, double aP, double aN, Location location, Path nowPath,double distanceInNowPath, Rectangle shape) {
        this.width = width;
        this.length = length;
        this.maxV = maxV;
        this.v = v;
        this.aP = aP;
        this.aN = aN;
        this.location=location;
        this.nowPath = nowPath;
        this.distanceInNowPath=distanceInNowPath;
        this.shape = shape;
    }

    public void updateShape() {
        shape.setRotate(location.getAngle());
        shape.setX(location.getX()-length/2);
        shape.setY(location.getY()-width/2);
    }

    public void updateV(boolean up){
        double maxVPath=nowPath.getMaxV();
        if (up){
            double tempV=v+aP/100;
            if(tempV<maxV && tempV<maxVPath) v=tempV;
            else updateV(false);
        } else{
            double tempV=v+aN/100;
            if (tempV>0) v=tempV;
            else v=0;
        }
    }
    public Vehicle getFrontVehicle(){
        double min=Double.MAX_VALUE;
        Vehicle frontVehicle=null;
        if (nowPath.getClass()==Line.class){
            Line nowLine=(Line) nowPath;
            double dirX,dirY;
            if (nowLine.getX2()-nowLine.getX1()<0) dirX=-1;
            else dirX=1;
            if (nowLine.getY2()-nowLine.getY1()<0) dirY=-1;
            else dirY=1;
            for (Vehicle vehicle: nowPath.getVehicles()) {
                if ((vehicle.location.getX()-location.getX())*dirX>0 || (vehicle.location.getY()-location.getY())*dirY>0){
                    double dR=Math.sqrt(Math.pow(vehicle.location.getX()-location.getX(),2)+Math.pow(vehicle.location.getY()-location.getY(),2));
                    if (dR<min){
                        min=dR;
                        frontVehicle=vehicle;
                    }
                }
            }
        }
        else if (nowPath.getClass()==Curve.class){
            Curve curve=(Curve) nowPath;
            double dirTeta=(curve.getTeta2()-curve.getTeta1())/Math.abs(curve.getTeta2()-curve.getTeta1());
            for (Vehicle vehicle: nowPath.getVehicles()) {
                double dT=(curve.getTetaFromXY(vehicle.location.getX(),vehicle.location.getY())-curve.getTetaFromXY(location.getX(),location.getY()))*dirTeta;
                if (dT>0){
                    if (dT<min){
                        min=dT;
                        frontVehicle=vehicle;
                    }
                }
            }
        }
        return frontVehicle;
    }
    public double compareToByDistanceInNowPath(Vehicle v){
        return this.distanceInNowPath-v.distanceInNowPath;
    }
}
