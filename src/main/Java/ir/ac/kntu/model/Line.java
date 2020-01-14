package ir.ac.kntu.model;

public class Line extends Path {
    private double x1,y1;
    private double x2,y2;
    private Line leftLine;
    private Line rightLine;

    public Line(String id, double maxV, ESPoint d1, ESPoint d2, double x1, double y1, double x2, double y2, Line leftLine, Line rightLine) {
        super(id, maxV, d1, d2);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.leftLine = leftLine;
        this.rightLine = rightLine;
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

}
