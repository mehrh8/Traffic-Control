package ir.ac.kntu.model;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

public class MiniCar extends Vehicle implements Runnable{
    private Vehicle thisObject = this;
    private double xt = 0, yt = 0;
    private int counterTimer = 0;
    private boolean isFrontVehicle = false;
    private boolean betaMode=false;
    private boolean betaModeBack=false;
    private boolean betaModeTest=false;
    private int selectColor;

    public MiniCar(double maxV, double v, double aP, double aN,Location location, Path nowPath,double distanceInNowPath, Rectangle shape, int id,int selectColor) {
        super(15, 30, maxV, v, aP, aN, location, nowPath,distanceInNowPath, shape,id);
        this.selectColor=selectColor;
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
                    calcIsBackVehicle();
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
                            if (nowPath.getNext().calcDistance(nowPath.getNext().getVehicles().get(0).location, nowPath.getNext().getStartLocation()) + nowPath.calcDistance(location, nowPath.getEndLocation()) < 110) {
                                if(nowPath.getNext().getVehicles().get(0).getClass()!=Motorcycle.class){
                                    isFrontVehicle = true;
                                    betaMode=false;
                                }else {
                                    isFrontVehicle = false;
                                    if (nowPath.getNext().getVehicles().get(0).getV()<v) {
                                        betaMode = true;
                                    }else{
                                        betaMode=false;
                                    }
                                }
                            } else {
                                isFrontVehicle = false;
                                betaMode=false;
                            }
                        } else {
                            isFrontVehicle = false;
                            betaMode=false;
                        }
                    } else {
                        isFrontVehicle = false;
                        betaMode=false;
                    }
                } else {
                    index = nowPath.getIndexVehicle(thisObject);
                    if (index != -1 && nowPath.getVehicles().get(index) != null && nowPath.getVehicles().get(index + 1) != null) {
                        if (nowPath.calcDistance(nowPath.getVehicles().get(index).location, nowPath.getVehicles().get(index + 1).location) < 110){
                            if(nowPath.getVehicles().get(index + 1).getClass()!=Motorcycle.class){
                                isFrontVehicle = true;
                                betaMode=false;
                            } else {
                                isFrontVehicle = false;
                                if (nowPath.getVehicles().get(index + 1).getV()<v) {
                                    betaMode = true;
                                }else{
                                    betaMode=false;
                                }
                            }
                        } else {
                            isFrontVehicle = false;
                            betaMode = false;
                        }
                    }
                }
            }
            counterTimer = 0;
        }
    }

    public void calcIsBackVehicle() {
        counterTimer += 1;
        if (counterTimer == 5) {
            synchronized (nowPath.getVehicles()) {
                nowPath.sortVehicles();
                int nowpathVehiclesSize = nowPath.getVehicles().size();
                int index = nowPath.getIndexVehicle(thisObject);
                if (index == 0) {
                    if (nowPath.getPrevious() != null) {
                        if (nowPath.getPrevious().getVehicles().size() > 0) {
                            nowPath.getPrevious().sortVehicles();
                            if (nowPath.getPrevious().calcDistance(nowPath.getPrevious().getVehicles().get(nowPath.getPrevious().getVehicles().size()-1).location, nowPath.getPrevious().getEndLocation()) + nowPath.calcDistance(location, nowPath.getStartLocation()) < 110) {
                                if(nowPath.getPrevious().getVehicles().get(nowPath.getPrevious().getVehicles().size()-1).getClass()!=Motorcycle.class){
                                    betaMode=false;
                                }else {
                                    betaModeBack=true;
                                }
                            } else {
                                betaModeBack=false;
                            }
                        } else {
                            betaModeBack=false;
                        }
                    } else {
                        betaModeBack=false;
                    }
                } else {
                    index = nowPath.getIndexVehicle(thisObject);
                    if (index != -1 && nowPath.getVehicles().get(index) != null && nowPath.getVehicles().get(index - 1) != null) {
                        if (nowPath.calcDistance(nowPath.getVehicles().get(index).location, nowPath.getVehicles().get(index - 1).location) < 110){
                            if(nowPath.getVehicles().get(index - 1).getClass()!=Motorcycle.class){
                                betaModeBack=false;
                            } else {
                                betaModeBack=true;
                            }
                        } else {
                            betaModeBack = false;
                        }
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
            if (v < pathRight.getMaxV()) {
                pathRight.sortVehicles();
                Vehicle nextVehicle = pathRight.findFront(this.location);
                Vehicle backVehicle = pathRight.findBack(this.location);
                if (nextVehicle!=null) {
                    if (nextVehicle.getClass() == Motorcycle.class) nextVehicle = null;
                }
                if (backVehicle!=null) {
                    if (backVehicle.getClass() == Motorcycle.class) backVehicle = null;
                }

                if(nextVehicle==null && backVehicle==null){
                    this.setLocation(nowPath.findLocationInCurrnetPathRight(this.location));
                    return 1;
                }else if (nextVehicle==null){
                    if (pathRight.findBackDistance(nowPath.findLocationInCurrnetPathRight(this.location)) > 100){
                        this.setLocation(nowPath.findLocationInCurrnetPathRight(this.location));
                        return 1;
                    }
                }else if (backVehicle==null){
                    if (pathRight.findFrontDistance(nowPath.findLocationInCurrnetPathRight(this.location)) > 100){
                        this.setLocation(nowPath.findLocationInCurrnetPathRight(this.location));
                        return 1;
                    }
                }else{
                    if (pathRight.findFrontDistance(nowPath.findLocationInCurrnetPathRight(this.location)) > 100 && pathRight.findBackDistance(nowPath.findLocationInCurrnetPathRight(this.location)) > 100) {
                        this.setLocation(nowPath.findLocationInCurrnetPathRight(this.location));
                        return 1;
                    }
                }


            }
        }
        if (pathLeft!=null) {
            if (v < pathLeft.getMaxV()) {
                pathLeft.sortVehicles();
                Vehicle nextVehicle = pathLeft.findFront(this.location);
                Vehicle backVehicle = pathLeft.findBack(this.location);
                if (nextVehicle!=null) {
                    if (nextVehicle.getClass() == Motorcycle.class) nextVehicle = null;
                }
                if (backVehicle!=null) {
                    if (backVehicle.getClass() == Motorcycle.class) backVehicle = null;
                }

                if (nextVehicle==null && backVehicle==null){
                    this.setLocation(nowPath.findLocationInCurrnetPathLeft(this.location));
                    return -1;
                } else if (nextVehicle==null){
                    if (pathLeft.findBackDistance(nowPath.findLocationInCurrnetPathLeft(this.location)) > 100){
                        this.setLocation(nowPath.findLocationInCurrnetPathLeft(this.location));
                        return -1;
                    }
                }else if (backVehicle==null){
                    if (pathLeft.findFrontDistance(nowPath.findLocationInCurrnetPathLeft(this.location)) > 100){
                        this.setLocation(nowPath.findLocationInCurrnetPathLeft(this.location));
                        return -1;
                    }
                }else {
                    if (pathLeft.findFrontDistance(nowPath.findLocationInCurrnetPathLeft(this.location)) > 100 && pathLeft.findBackDistance(nowPath.findLocationInCurrnetPathLeft(this.location)) > 100) {
                        this.setLocation(nowPath.findLocationInCurrnetPathLeft(this.location));
                        return -1;
                    }
                }
            }
        }
        return 0;
    }
    @Override
    public void updateShape() throws FileNotFoundException {
        if (!betaMode && !betaModeBack) {
            if (!betaModeTest) {
                betaModeTest= false;
                shape.setRotate(location.getAngle());
                shape.setX(location.getX() - length / 2);
                shape.setY(location.getY() - width / 2);
            }else {
                betaModeTest=false;
                FileInputStream input = null;
                if (selectColor==1) input=new FileInputStream("src\\main\\Java\\ir\\ac\\kntu\\Image\\Car_Red.png");
                else if (selectColor==2) input=new FileInputStream("src\\main\\Java\\ir\\ac\\kntu\\Image\\Car_Green.png");
                else if (selectColor==3) input=new FileInputStream("src\\main\\Java\\ir\\ac\\kntu\\Image\\Car_Blue.png");
                else if (selectColor==4) input=new FileInputStream("src\\main\\Java\\ir\\ac\\kntu\\Image\\Car_Yellow.png");
                Image image = new Image(input);
                ImagePattern image_pattern = new ImagePattern(image);
                shape.setFill(image_pattern);
                shape.setWidth(30);
                shape.setHeight(15);
                width=15;
                length=30;
            }
        } else{
            if (!betaModeTest) {
                betaModeTest= true;
                FileInputStream input = null;
                if (selectColor==1) input=new FileInputStream("src\\main\\Java\\ir\\ac\\kntu\\Image\\Car_Red_Beta.png");
                else if (selectColor==2) input=new FileInputStream("src\\main\\Java\\ir\\ac\\kntu\\Image\\Car_Green_Beta.png");
                else if (selectColor==3) input=new FileInputStream("src\\main\\Java\\ir\\ac\\kntu\\Image\\Car_Blue_Beta.png");
                else if (selectColor==4) input=new FileInputStream("src\\main\\Java\\ir\\ac\\kntu\\Image\\Car_Yellow_Beta.png");
                Image image = new Image(input);
                ImagePattern image_pattern = new ImagePattern(image);
                shape.setFill(image_pattern);
                shape.setWidth(30);
                shape.setHeight(30);
                width=30;
                length=30;
            }else {
                betaModeTest= true;
                shape.setRotate(location.getAngle());
                shape.setX(location.getX() - length / 2);
                shape.setY(location.getY() - width / 2);
            }
        }
    }
}
