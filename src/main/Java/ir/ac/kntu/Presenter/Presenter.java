package ir.ac.kntu.Presenter;

import ir.ac.kntu.View.*;
import ir.ac.kntu.model.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Presenter {
    private double maxVCar=100,v0Car=0,aPCar=20,aNCar=-20,addPerSecondCar=2;
    private double maxVMiniCar=120,v0MiniCar=0,aPMiniCar=30,aNMiniCar=-40,addPerSecondMiniCar=2;
    private double maxVTruck=60,v0Truck=0,aPTruck=15,aNTruck=-15,addPerSecondTruck=2;
    private double maxVMotorcycle=120,v0Motorcycle=0,aPMotorcycle=40,aNMotorcycle=-20,addPerSecondMotorcycle=2;
    private int colorCar=1,colorMiniCar=2,colorTruck=3,colorMotorcycle=4;
    private double minDistance=100,counterCar=0,counterMiniCar=0,counterTruck=0,counterMotorcycle=0;
    private Map map=new Map();
    private Group root;
    private Scene scene;
    private ArrayList<Path> startPaths;
    private ArrayList<Path> startPathsTruck;
    private ArrayList<Vehicle> vehicles=new ArrayList<>();

    public Group getRoot() {
        return root;
    }

    public void setRoot(Group root) {
        this.root = root;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Presenter(Group root, Scene scene) {
        this.root = root;
        this.scene = scene;
    }

    public int getRandomInt(int from,int to){
        return ((int)(Math.random()*(to-from+1)))+from;
    }
    public void run() throws FileNotFoundException {
        map.read("src\\main\\Java\\ir\\ac\\kntu\\Maps\\Map1.txt");
        map.setMapToView(this);
        startPaths = map.getStartPaths();
        startPathsTruck = map.getStartPathsTruck();
        ArrayList<Shape> carShapes=new ArrayList<>();
        ArrayList<Shape> miniCarShapes=new ArrayList<>();
        ArrayList<Shape> truckShapes=new ArrayList<>();
        ArrayList<Shape> motorCycleShapes=new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Shape carShape=CarShape.getShape(colorCar);
            Shape miniCarShape= MiniCarShape.getShape(colorMiniCar);
            Shape truckShape= TruckShape.getShape(colorTruck);
            Shape motorCycleShape= MotorcycleShape.getShape(colorMotorcycle);
            carShapes.add(carShape);
            miniCarShapes.add(miniCarShape);
            truckShapes.add(truckShape);
            motorCycleShapes.add(motorCycleShape);
            root.getChildren().addAll(carShape,miniCarShape,truckShape,motorCycleShape);
        }
        final int[] co = {0,0,0,0,0};
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < addPerSecondCar; i++) {
                    int randStartPath = getRandomInt(0, startPaths.size() - 1);
                    Car car = null;
                    car = new Car(maxVCar, v0Car, aPCar, aNCar, startPaths.get(randStartPath).getStartLocation(), startPaths.get(randStartPath), 0,(Rectangle) carShapes.get(co[1] %1000),co[0]);
                    co[0] +=1;
                    co[1]+=1;
                    vehicles.add(car);
                    Thread temp = new Thread(car);
                    temp.start();
                    car.setMythread(temp);
                    startPaths.get(randStartPath).addVehicle(car);
                    counterCar+=1;
                }
                for (int i = 0; i < addPerSecondMiniCar; i++) {
                    int randStartPath = getRandomInt(0, startPaths.size() - 1);
                    MiniCar miniCar = null;
                    miniCar = new MiniCar(maxVMiniCar, v0MiniCar, aPMiniCar, aNMiniCar, startPaths.get(randStartPath).getStartLocation(), startPaths.get(randStartPath), 0,(Rectangle) miniCarShapes.get(co[2] %1000),co[0]);
                    co[0]+=1;
                    co[2]+=1;
                    vehicles.add(miniCar);
                    Thread temp = new Thread(miniCar);
                    temp.start();
                    miniCar.setMythread(temp);
                    startPaths.get(randStartPath).addVehicle(miniCar);
                    counterMiniCar+=1;
                }
                for (int i = 0; i < addPerSecondTruck; i++) {
                    int randStartPath = getRandomInt(0, startPathsTruck.size() - 1);
                    Truck truck = null;
                    truck = new Truck(maxVTruck, v0Truck, aPTruck, aNTruck, startPathsTruck.get(randStartPath).getStartLocation(), startPathsTruck.get(randStartPath), 0,(Rectangle) truckShapes.get(co[3] %1000),co[0]);
                    co[0] +=1;
                    co[3]+=1;
                    vehicles.add(truck);
                    Thread temp = new Thread(truck);
                    temp.start();
                    truck.setMythread(temp);
                    startPathsTruck.get(randStartPath).addVehicle(truck);
                    counterTruck+=1;
                }
                for (int i = 0; i < addPerSecondMotorcycle; i++) {
                    int randStartPath = getRandomInt(0, startPaths.size() - 1);
                    Motorcycle motorcycle = null;
                    motorcycle = new Motorcycle(maxVMotorcycle, v0Motorcycle, aPMotorcycle, aNMotorcycle, startPaths.get(randStartPath).getStartLocation(), startPaths.get(randStartPath), 0,(Rectangle) motorCycleShapes.get(co[4] %1000),co[0]);
                    co[0] +=1;
                    co[4]+=1;
                    vehicles.add(motorcycle);
                    Thread temp = new Thread(motorcycle);
                    temp.start();
                    motorcycle.setMythread(temp);
                    startPaths.get(randStartPath).addVehicle(motorcycle);
                    counterMotorcycle+=1;
                }
            }
        },0,4000);
    }
}
