package ir.ac.kntu.Presenter;

import ir.ac.kntu.View.*;
import ir.ac.kntu.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Presenter {
    private double maxVCar=100,v0Car=0,aPCar=20,aNCar=-20,addPerSecondCar=1;
    private double maxVMiniCar=50,v0MiniCar=0,aPMiniCar=30,aNMiniCar=-40,addPerSecondMiniCar=1;
    private double maxVTruck=60,v0Truck=0,aPTruck=15,aNTruck=-15,addPerSecondTruck=1;
    private double maxVMotorcycle=120,v0Motorcycle=0,aPMotorcycle=40,aNMotorcycle=-20,addPerSecondMotorcycle=1;
    private int colorCar=1,colorMiniCar=2,colorTruck=3,colorMotorcycle=4;
    private double minDistance=100,counterCar=0,counterMiniCar=0,counterTruck=0,counterMotorcycle=0;
    private int numberOfCar,numberOfMiniCar,numberOfTruck,numberOfMotorcycle;
    private int selectMap=1;
    private Map map=new Map();
    private Group root;
    private Scene scene;
    GridPane grid = new GridPane();
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
        scene.setRoot(grid);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        final Label carLabel = new Label("Car: ");
        GridPane.setConstraints(carLabel, 0, 1);
        GridPane.setColumnSpan(carLabel, 1);
        final Label miniCarLabel = new Label("Mini Car: ");
        GridPane.setConstraints(miniCarLabel, 0, 2);
        GridPane.setColumnSpan(miniCarLabel, 1);
        final Label motorcycleLabel = new Label("Motorcycle: ");
        GridPane.setConstraints(motorcycleLabel, 0, 3);
        GridPane.setColumnSpan(motorcycleLabel, 1);
        final Label truckLabel = new Label("Truck: ");
        GridPane.setConstraints(truckLabel, 0, 4);
        GridPane.setColumnSpan(truckLabel, 1);
        final Label mapLabel = new Label("map");
        GridPane.setConstraints(mapLabel, 0, 5);
        GridPane.setColumnSpan(mapLabel, 1);
        final Label minDistanceLabel = new Label("min distance");
        GridPane.setConstraints(minDistanceLabel, 0, 6);
        GridPane.setColumnSpan(minDistanceLabel, 1);

        final Label maxVLabel = new Label("max V");
        GridPane.setConstraints(maxVLabel, 1, 0);
        GridPane.setColumnSpan(maxVLabel, 1);
        final Label v0Label = new Label("V0");
        GridPane.setConstraints(v0Label, 2, 0);
        GridPane.setColumnSpan(v0Label, 1);
        final Label aPLabel = new Label("aP");
        GridPane.setConstraints(aPLabel, 3, 0);
        GridPane.setColumnSpan(aPLabel, 1);
        final Label aNLabel = new Label("aN");
        GridPane.setConstraints(aNLabel, 4, 0);
        GridPane.setColumnSpan(aNLabel, 1);
        final Label addPerSecondLabel = new Label("addPerSecond");
        GridPane.setConstraints(addPerSecondLabel, 5, 0);
        GridPane.setColumnSpan(addPerSecondLabel, 1);
        final Label colorLabel = new Label("color");
        GridPane.setConstraints(colorLabel, 6, 0);
        GridPane.setColumnSpan(colorLabel, 1);
        final Label numberOfLabel = new Label("numberOF");
        GridPane.setConstraints(numberOfLabel, 7, 0);
        GridPane.setColumnSpan(numberOfLabel, 1);
        grid.getChildren().addAll(carLabel,miniCarLabel,motorcycleLabel,truckLabel,maxVLabel,v0Label,aPLabel,aNLabel,addPerSecondLabel,colorLabel,mapLabel,numberOfLabel,minDistanceLabel);

        final TextField maxVFieldCar = getField("100",1,1);
        final TextField v0FieldCar = getField("0",2,1);
        final TextField aPFieldCar = getField("20",3,1);
        final TextField aNFieldCar = getField("-20",4,1);
        final TextField addPerSecondFieldCar = getField("1",5,1);
        final TextField colorFieldCar = getField("1",6,1);
        final TextField numberOfFieldCar = getField("50",7,1);
        grid.getChildren().addAll(maxVFieldCar,v0FieldCar,aPFieldCar,aNFieldCar,addPerSecondFieldCar,colorFieldCar,numberOfFieldCar);

        final TextField maxVFieldMiniCar = getField("80",1,2);
        final TextField v0FieldMiniCar = getField("0",2,2);
        final TextField aPFieldMiniCar = getField("30",3,2);
        final TextField aNFieldMiniCar = getField("-40",4,2);
        final TextField addPerSecondFieldMiniCar = getField("2",5,2);
        final TextField colorFieldMiniCar = getField("2",6,2);
        final TextField numberOfFieldMiniCar = getField("50",7,2);
        grid.getChildren().addAll(maxVFieldMiniCar,v0FieldMiniCar,aPFieldMiniCar,aNFieldMiniCar,addPerSecondFieldMiniCar,colorFieldMiniCar,numberOfFieldMiniCar);

        final TextField maxVFieldMotorcycle = getField("120",1,3);
        final TextField v0FieldMotorcycle = getField("0",2,3);
        final TextField aPFieldMotorcycle = getField("40",3,3);
        final TextField aNFieldMotorcycle = getField("-20",4,3);
        final TextField addPerSecondFieldMotorcycle = getField("1",5,3);
        final TextField colorFieldMotorcycle = getField("3",6,3);
        final TextField numberOfFieldMotorcycle = getField("50",7,3);
        grid.getChildren().addAll(maxVFieldMotorcycle,v0FieldMotorcycle,aPFieldMotorcycle,aNFieldMotorcycle,addPerSecondFieldMotorcycle,colorFieldMotorcycle,numberOfFieldMotorcycle);

        final TextField maxVFieldTruck = getField("60",1,4);
        final TextField v0FieldTruck = getField("0",2,4);
        final TextField aPFieldTruck = getField("15",3,4);
        final TextField aNFieldTruck = getField("-15",4,4);
        final TextField addPerSecondFieldTruck = getField("1",5,4);
        final TextField colorFieldTruck = getField("4",6,4);
        final TextField numberOfFieldTruck = getField("50",7,4);
        grid.getChildren().addAll(maxVFieldTruck,v0FieldTruck,aPFieldTruck,aNFieldTruck,addPerSecondFieldTruck,colorFieldTruck,numberOfFieldTruck);

        final TextField selectMapField = getField("1",1,5);
        grid.getChildren().addAll(selectMapField);
        final TextField minDistanceField = getField("100",1,6);
        grid.getChildren().addAll(minDistanceField);

        final Button[] submit = {new Button("Submit")};
        GridPane.setConstraints(submit[0], 0, 8);
        grid.getChildren().add(submit[0]);

        submit[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                boolean isValid=true;
                if (maxVFieldCar.getText()==null || maxVFieldCar.getText().isEmpty() ||
                        v0FieldCar.getText()==null || v0FieldCar.getText().isEmpty() ||
                        aPFieldCar.getText()==null || aPFieldCar.getText().isEmpty() ||
                        aNFieldCar.getText()==null || aNFieldCar.getText().isEmpty() ||
                        addPerSecondFieldCar.getText()==null || addPerSecondFieldCar.getText().isEmpty() ||
                        colorFieldCar.getText()==null || colorFieldCar.getText().isEmpty() ||
                        numberOfFieldCar.getText()==null || numberOfFieldCar.getText().isEmpty() ||
                        maxVFieldMiniCar.getText()==null || maxVFieldMiniCar.getText().isEmpty() ||
                        v0FieldMiniCar.getText()==null || v0FieldMiniCar.getText().isEmpty() ||
                        aPFieldMiniCar.getText()==null || aPFieldMiniCar.getText().isEmpty() ||
                        aNFieldMiniCar.getText()==null || aNFieldMiniCar.getText().isEmpty() ||
                        addPerSecondFieldMiniCar.getText()==null || addPerSecondFieldMiniCar.getText().isEmpty() ||
                        colorFieldMiniCar.getText()==null || colorFieldMiniCar.getText().isEmpty() ||
                        numberOfFieldMiniCar.getText()==null || numberOfFieldMiniCar.getText().isEmpty() ||
                        maxVFieldMotorcycle.getText()==null || maxVFieldMotorcycle.getText().isEmpty() ||
                        v0FieldMotorcycle.getText()==null || v0FieldMotorcycle.getText().isEmpty() ||
                        aPFieldMotorcycle.getText()==null || aPFieldMotorcycle.getText().isEmpty() ||
                        aNFieldMotorcycle.getText()==null || aNFieldMotorcycle.getText().isEmpty() ||
                        addPerSecondFieldMotorcycle.getText()==null || addPerSecondFieldMotorcycle.getText().isEmpty() ||
                        colorFieldMotorcycle.getText()==null || colorFieldMotorcycle.getText().isEmpty() ||
                        numberOfFieldMotorcycle.getText()==null || numberOfFieldMotorcycle.getText().isEmpty() ||
                        maxVFieldTruck.getText()==null || maxVFieldTruck.getText().isEmpty() ||
                        v0FieldTruck.getText()==null || v0FieldTruck.getText().isEmpty() ||
                        aPFieldTruck.getText()==null || aPFieldTruck.getText().isEmpty() ||
                        aNFieldTruck.getText()==null || aNFieldTruck.getText().isEmpty() ||
                        addPerSecondFieldTruck.getText()==null || addPerSecondFieldTruck.getText().isEmpty() ||
                        colorFieldTruck.getText()==null || colorFieldTruck.getText().isEmpty() ||
                        numberOfFieldTruck.getText()==null || numberOfFieldTruck.getText().isEmpty() ||
                        selectMapField.getText()==null || selectMapField.getText().isEmpty() ||
                        minDistanceField.getText()==null || minDistanceField.getText().isEmpty()
                ) {
                    isValid=false;
                }
                if (isValid) {
                    maxVCar = Double.parseDouble(maxVFieldCar.getText());
                    v0Car = Double.parseDouble(v0FieldCar.getText());
                    aPCar = Double.parseDouble(aPFieldCar.getText());
                    aNCar = Double.parseDouble(aNFieldCar.getText());
                    addPerSecondCar = Integer.parseInt(addPerSecondFieldCar.getText());

                    maxVMiniCar = Double.parseDouble(maxVFieldMiniCar.getText());
                    v0MiniCar = Double.parseDouble(v0FieldMiniCar.getText());
                    aPMiniCar = Double.parseDouble(aPFieldMiniCar.getText());
                    aNMiniCar = Double.parseDouble(aNFieldMiniCar.getText());
                    addPerSecondMiniCar = Integer.parseInt(addPerSecondFieldMiniCar.getText());

                    maxVMotorcycle = Double.parseDouble(maxVFieldMotorcycle.getText());
                    v0Motorcycle = Double.parseDouble(v0FieldMotorcycle.getText());
                    aPMotorcycle = Double.parseDouble(aPFieldMotorcycle.getText());
                    aNMotorcycle = Double.parseDouble(aNFieldMotorcycle.getText());
                    addPerSecondMotorcycle = Integer.parseInt(addPerSecondFieldMotorcycle.getText());

                    maxVTruck = Double.parseDouble(maxVFieldTruck.getText());
                    v0Truck = Double.parseDouble(v0FieldTruck.getText());
                    aPTruck = Double.parseDouble(aPFieldTruck.getText());
                    aNTruck = Double.parseDouble(aNFieldTruck.getText());
                    addPerSecondTruck = Integer.parseInt(addPerSecondFieldTruck.getText());

                    colorCar = Integer.parseInt(colorFieldCar.getText());
                    colorMiniCar = Integer.parseInt(colorFieldMiniCar.getText());
                    ;
                    colorTruck = Integer.parseInt(colorFieldTruck.getText());
                    ;
                    colorMotorcycle = Integer.parseInt(colorFieldMotorcycle.getText());
                    ;
                    minDistance = Double.parseDouble(minDistanceField.getText());
                    counterCar = 0;
                    counterMiniCar = 0;
                    counterTruck = 0;
                    counterMotorcycle = 0;
                    numberOfCar = Integer.parseInt(numberOfFieldCar.getText());
                    numberOfMiniCar = Integer.parseInt(numberOfFieldMiniCar.getText());
                    numberOfTruck = Integer.parseInt(numberOfFieldTruck.getText());
                    numberOfMotorcycle = Integer.parseInt(numberOfFieldMotorcycle.getText());

                    selectMap = Integer.parseInt(selectMapField.getText());
                    try {
                        mainRun();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        //mainRun();
    }
    public void mainRun() throws FileNotFoundException {
        scene.setRoot(root);
        if (selectMap==1) map.read("src\\main\\Java\\ir\\ac\\kntu\\Maps\\Map1.txt");
        else if (selectMap==2) map.read("src\\main\\Java\\ir\\ac\\kntu\\Maps\\Map2.txt");
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
        Text numberOfCarText=new Text(700,400,"");
        numberOfCarText.setFill(Color.WHITE);
        Text numberOfMiniCarText=new Text(700,450,"");
        numberOfMiniCarText.setFill(Color.WHITE);
        Text numberOfMotorcycleText=new Text(700,500,"");
        numberOfMotorcycleText.setFill(Color.WHITE);
        Text numberOfTruckText=new Text(700,550,"");
        numberOfTruckText.setFill(Color.WHITE);
        root.getChildren().addAll(numberOfCarText,numberOfMiniCarText,numberOfTruckText,numberOfMotorcycleText);
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < addPerSecondCar; i++) {
                    if (counterCar==numberOfCar) break;
                    int randStartPath = getRandomInt(0, startPaths.size() - 1);
                    Car car = null;
                    car = new Car(maxVCar, v0Car, aPCar, aNCar, startPaths.get(randStartPath).getStartLocation(), startPaths.get(randStartPath), 0,(Rectangle) carShapes.get(co[1] %1000),co[0],minDistance);
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
                    if (counterMiniCar==numberOfMiniCar) break;
                    int randStartPath = getRandomInt(0, startPaths.size() - 1);
                    MiniCar miniCar = null;
                    miniCar = new MiniCar(maxVMiniCar, v0MiniCar, aPMiniCar, aNMiniCar, startPaths.get(randStartPath).getStartLocation(), startPaths.get(randStartPath), 0,(Rectangle) miniCarShapes.get(co[2] %1000),co[0],colorMiniCar,minDistance);
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
                    if (counterTruck==numberOfTruck) break;
                    int randStartPath = getRandomInt(0, startPathsTruck.size() - 1);
                    Truck truck = null;
                    truck = new Truck(maxVTruck, v0Truck, aPTruck, aNTruck, startPathsTruck.get(randStartPath).getStartLocation(), startPathsTruck.get(randStartPath), 0,(Rectangle) truckShapes.get(co[3] %1000),co[0],minDistance);
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
                    if (counterMotorcycle==numberOfMotorcycle) break;
                    int randStartPath = getRandomInt(0, startPaths.size() - 1);
                    Motorcycle motorcycle = null;
                    motorcycle = new Motorcycle(maxVMotorcycle, v0Motorcycle, aPMotorcycle, aNMotorcycle, startPaths.get(randStartPath).getStartLocation(), startPaths.get(randStartPath), 0,(Rectangle) motorCycleShapes.get(co[4] %1000),co[0],colorMotorcycle,minDistance);
                    co[0] +=1;
                    co[4]+=1;
                    vehicles.add(motorcycle);
                    Thread temp = new Thread(motorcycle);
                    temp.start();
                    motorcycle.setMythread(temp);
                    startPaths.get(randStartPath).addVehicle(motorcycle);
                    counterMotorcycle+=1;
                }
                numberOfCarText.setText("Counter Car: "+counterCar);
                numberOfMiniCarText.setText("Counter MiniCar: "+counterMiniCar);
                numberOfMotorcycleText.setText("Counter Motorcycle: "+counterMotorcycle);
                numberOfTruckText.setText("Counter Truck: "+counterTruck);
            }
        },0,4000);
    }
    public TextField getField(String text,int column,int row){
        final TextField field = new TextField();
        field.setPromptText(text);
        field.setPrefColumnCount(10);
        field.getText();
        field.setText(text);
        GridPane.setConstraints(field, column, row);
        return field;
    }
}
