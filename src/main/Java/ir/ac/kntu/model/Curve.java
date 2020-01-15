package ir.ac.kntu.model;

public class Curve extends Path {
    private double centerX, centerY, radius, teta1, teta2;
    private Curve leftCurve;
    private Curve rightCurve;
    private String leftId,rightId;

    public Curve(String id, double maxV, Boolean truck,String nextPath, double centerX, double centerY, double radius, double teta1, double teta2, String leftId, String rightId) {
        super(id, maxV, truck,nextPath);
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.teta1 = teta1;
        this.teta2 = teta2;
        this.leftId = leftId;
        this.rightId = rightId;
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

    public double getCenterX() {
        return centerX;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    public Curve getLeftCurve() {
        return leftCurve;
    }

    public void setLeftCurve(Curve leftCurve) {
        this.leftCurve = leftCurve;
    }

    public Curve getRightCurve() {
        return rightCurve;
    }

    public void setRightCurve(Curve rightCurve) {
        this.rightCurve = rightCurve;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getTeta1() {
        return teta1;
    }

    public void setTeta1(double teta1) {
        this.teta1 = teta1;
    }

    public double getTeta2() {
        return teta2;
    }

    public void setTeta2(double teta2) {
        this.teta2 = teta2;
    }

    public double getX(double teta) {
        return this.centerX + this.radius * Math.cos(teta);
    }

    public double getY(double teta) {
        return this.centerY - this.radius * Math.sin(teta);
    }

    public double getLengthTeta(){
        return (teta2-teta1)*180/Math.PI;
    }
    public double getStartTeta(){
        return teta1*180/Math.PI;
    }
}
