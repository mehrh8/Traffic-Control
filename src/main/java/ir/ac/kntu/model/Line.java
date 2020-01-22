package ir.ac.kntu.model;

import java.awt.geom.Line2D;

public class Line extends Path {
    private double x1,y1;
    private double x2,y2;
    private Line leftLine;
    private Line rightLine;
    private String leftId,rightId;

    public Line(String id, double maxV, Boolean truck,String nextPath, double x1, double y1, double x2, double y2, String leftId, String rightId) {
        super(id, maxV, truck,nextPath);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.leftId = leftId;
        this.rightId = rightId;
    }

    public Line getLeftLine() {
        return leftLine;
    }

    public void setLeftLine(Line leftLine) {
        this.leftLine = leftLine;
    }

    public Line getRightLine() {
        return rightLine;
    }

    public void setRightLine(Line rightLine) {
        this.rightLine = rightLine;
    }

    public String getLeftId() {
        return leftId;
    }

    public void setLeftId(String leftId) {
        this.leftId = leftId;
    }

    public String getRightId() {
        return rightId;
    }

    public void setRightId(String rightId) {
        this.rightId = rightId;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public double getXFromLeftLine(double x){
        double dx=leftLine.getX1()-x;
        return this.x1+dx;
    }
    public double getXFromRightLine(double x){
        double dx=rightLine.getX1()-x;
        return this.x1+dx;
    }
    public double getYFromLeftLine(double y){
        double dy=leftLine.getY1()-y;
        return this.y1+dy;
    }
    public double getYFromRightLine(double y){
        double dy=rightLine.getY1()-y;
        return this.y1+dy;
    }
    public double getLength(){
        return Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
    }
    public double getAngle(){
        double angle = Math.atan((y2-y1)/(x2-x1));
        if (x2<x1) angle+=Math.PI;
        return angle*180/Math.PI;
    }


    @Override
    public Location getNextLocation(Location nowLocation, double distance) {
        if(calcDistance(new Location(x2,y2,0),nowLocation)<distance) return null;
        double angle=Math.atan((y2-y1)/(x2-x1));
        if (y1>y2) angle+=Math.PI;
        else if (y1==y2 && x2<x1) angle+=Math.PI;
        return new Location(nowLocation.getX()+Math.cos(angle)*distance,nowLocation.getY()+Math.sin(angle)*distance,angle*180/Math.PI);
    }

    @Override
    public double calcDistance(Location location1, Location location2) {
        return Math.sqrt(Math.pow(location1.getX()-location2.getX(),2)+Math.pow(location1.getY()-location2.getY(),2));
    }
}
