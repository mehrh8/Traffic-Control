package ir.ac.kntu.model;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

public class Truck extends Vehicle implements Runnable{
    private Vehicle thisObject = this;
    private double xt = 0, yt = 0;
    private int counterTimer = 0;
    private boolean isFrontVehicle = false;
    private double fasele;

    public Truck(double maxV, double v, double aP, double aN,Location location, Path nowPath,double distanceInNowPath, Rectangle shape, int id,double fasele) {
        super(30, 45, maxV, v, aP, aN, location, nowPath,distanceInNowPath, shape,id);
        this.fasele=fasele;
    }


    @Override
    public void run() {
        Timer timer = new Timer();
        final int[] counter = {0};
        final Path[] isAntedate={null};
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                counter[0] +=1;
                if (hasEnd()) {
                    nowPath.getVehicles().remove(thisObject);
                    shape.setVisible(false);
                    mythread.stop();
                    v=0;
                } else {
                    calcIsFrontVehicle();
                    if (isFrontVehicle) {
                        updateV(false);
                    } else updateV(true);

                    Location newLocation = nowPath.getNextLocation(location, v / 100);
                    if (newLocation != null) {
                        location = newLocation;
                    } else {
                        synchronized (nowPath.getVehicles()) {
                            nowPath.sortVehicles();
                            nowPath.getVehicles().remove(thisObject);
                            nowPath = nowPath.getNext();
                            nowPath.getVehicles().add(thisObject);
                        }
                    }
                    distanceInNowPath = nowPath.calcDistance(location, nowPath.getStartLocation());

                    if (isAntedate[0]!=null && counter[0]%100==50){
                        synchronized (isAntedate[0].getVehicles()){
                            isAntedate[0].getVehicles().remove(thisObject);
                            isAntedate[0]=null;
                        }
                    }
                    if (isFrontVehicle && counter[0] %100==0) {
                        int testAntedate =antedate();
                        synchronized (nowPath.getVehicles()) {
                            nowPath.sortVehicles();
                            if (testAntedate == 1) {//Right
                                isAntedate[0]=nowPath;
                                if (nowPath.getClass() == Line.class) {
                                    ((Line) nowPath).getRightLine().addVehicle(thisObject);
                                    nowPath = ((Line) nowPath).getRightLine();
                                } else if (nowPath.getClass() == Curve.class) {
                                    ((Curve) nowPath).getRightCurve().addVehicle(thisObject);
                                    nowPath = ((Curve) nowPath).getRightCurve();
                                }
                            } else if (testAntedate == -1) {//Left
                                isAntedate[0]=nowPath;
                                if (nowPath.getClass() == Line.class) {
                                    ((Line) nowPath).getLeftLine().addVehicle(thisObject);
                                    nowPath = ((Line) nowPath).getLeftLine();
                                } else if (nowPath.getClass() == Curve.class) {
                                    ((Curve) nowPath).getLeftCurve().addVehicle(thisObject);
                                    nowPath = ((Curve) nowPath).getLeftCurve();
                                }
                            }
                        }
                    }
                }
            }
        }, 0, 10);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                try {
                    updateShape();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        animationTimer.start();
    }

    public void calcIsFrontVehicle() {
        counterTimer += 1;
        if (counterTimer == 5) {
            synchronized (nowPath.getVehicles()) {
                nowPath.sortVehicles();
                int nowpathVehiclesSize = nowPath.getVehicles().size();
                int index = nowPath.getIndexVehicle(thisObject);
                if (index == nowpathVehiclesSize - 1) {
                    if (nowPath.getNext() != null) {
                        if (nowPath.getNext().getVehicles().size() > 0) {
                            nowPath.getNext().sortVehicles();
                            if (nowPath.getNext().calcDistance(nowPath.getNext().getVehicles().get(0).location, nowPath.getNext().getStartLocation()) + nowPath.calcDistance(location, nowPath.getEndLocation()) < fasele+10) {
                                isFrontVehicle = true;
                            } else isFrontVehicle = false;

                        } else isFrontVehicle = false;
                    } else isFrontVehicle = false;
                } else {
                    index = nowPath.getIndexVehicle(thisObject);
                    if (index != -1 && nowPath.getVehicles().get(index) != null && nowPath.getVehicles().get(index + 1) != null) {
                        if (nowPath.calcDistance(nowPath.getVehicles().get(index).location, nowPath.getVehicles().get(index + 1).location) < fasele+10)
                            isFrontVehicle = true;
                        else isFrontVehicle = false;
                    }
                }
            }
            counterTimer = 0;
        }
    }

    @Override
    public int antedate() {
        Path pathRight = null;
        Path pathLeft = null;
        if (nowPath.getClass() == Line.class) {
            pathRight = ((Line) nowPath).getRightLine();
            pathLeft = ((Line) nowPath).getLeftLine();
        } else if (nowPath.getClass() == Curve.class) {
            pathRight = ((Curve) nowPath).getRightCurve();
            pathLeft = ((Curve) nowPath).getLeftCurve();
        }
        if (pathRight!=null) {
            if (pathRight.getTruck()) {
                if (v < pathRight.getMaxV()) {
                    pathRight.sortVehicles();
                    Vehicle nextVehicle = pathRight.findFront(this.location);
                    Vehicle backVehicle = pathRight.findBack(this.location);

                    if (nextVehicle == null && backVehicle == null) {
                        this.setLocation(nowPath.findLocationInCurrnetPathRight(this.location));
                        return 1;
                    } else if (nextVehicle == null) {
                        if (pathRight.findBackDistance(nowPath.findLocationInCurrnetPathRight(this.location)) > fasele) {
                            this.setLocation(nowPath.findLocationInCurrnetPathRight(this.location));
                            return 1;
                        }
                    } else if (backVehicle == null) {
                        if (pathRight.findFrontDistance(nowPath.findLocationInCurrnetPathRight(this.location)) > fasele) {
                            this.setLocation(nowPath.findLocationInCurrnetPathRight(this.location));
                            return 1;
                        }
                    } else {
                        if (pathRight.findFrontDistance(nowPath.findLocationInCurrnetPathRight(this.location)) > fasele && pathRight.findBackDistance(nowPath.findLocationInCurrnetPathRight(this.location)) > fasele) {
                            this.setLocation(nowPath.findLocationInCurrnetPathRight(this.location));
                            return 1;
                        }
                    }
                }
            }
        }
        if (pathLeft!=null) {
            if (pathLeft.getTruck()) {
                if (v < pathLeft.getMaxV()) {
                    pathLeft.sortVehicles();
                    Vehicle nextVehicle = pathLeft.findFront(this.location);
                    Vehicle backVehicle = pathLeft.findBack(this.location);

                    if (nextVehicle == null && backVehicle == null) {
                        this.setLocation(nowPath.findLocationInCurrnetPathLeft(this.location));
                        return -1;
                    } else if (nextVehicle == null) {
                        if (pathLeft.findBackDistance(nowPath.findLocationInCurrnetPathLeft(this.location)) > fasele) {
                            this.setLocation(nowPath.findLocationInCurrnetPathLeft(this.location));
                            return -1;
                        }
                    } else if (backVehicle == null) {
                        if (pathLeft.findFrontDistance(nowPath.findLocationInCurrnetPathLeft(this.location)) > fasele) {
                            this.setLocation(nowPath.findLocationInCurrnetPathLeft(this.location));
                            return -1;
                        }
                    } else {
                        if (pathLeft.findFrontDistance(nowPath.findLocationInCurrnetPathLeft(this.location)) > fasele && pathLeft.findBackDistance(nowPath.findLocationInCurrnetPathLeft(this.location)) > fasele) {
                            this.setLocation(nowPath.findLocationInCurrnetPathLeft(this.location));
                            return -1;
                        }
                    }
                }
            }
        }
        return 0;
    }
}
