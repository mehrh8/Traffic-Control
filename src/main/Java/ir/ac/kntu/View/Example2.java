/*package ir.ac.kntu.View;

import ir.ac.kntu.model.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Example2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        Group root = new Group();
        Scene scene = new Scene(root, 1600, 1000, new ImagePattern(new Image(new FileInputStream("src\\main\\Java\\ir\\ac\\kntu\\Image\\grass.png"))));
        stage.setScene(scene);
        stage.setTitle("Test");
        stage.setResizable(false);
        stage.show();
        Map map = new Map();
        map.read("src\\main\\Java\\ir\\ac\\kntu\\Maps\\Map1.txt");
        for (int i = 0; i < 6; i++) {
            LineShape.makeShape(root, (Line) map.getPaths().get(i));
        }
        for (int i = 6; i < 9; i++) {
            CurveShape.makeCurve(root, (Curve) map.getPaths().get(i));
        }
        //Test
        for (int i=0;i<3;i++) {
            ArrayList<Car> cars = new ArrayList<>();
            Car car1 = new Car(120, 0, 40, -20, new Location(1600, 130+35*i, 180), map.getPaths().get(i),0, CarShape.getShape(i+1));
            cars.add(car1);
            new Thread(car1).start();
            map.getPaths().get(i).getVehicles().add(car1);
            root.getChildren().add(car1.getShape());
        }
        for (int i=0;i<3;i++) {
            ArrayList<Car> cars = new ArrayList<>();
            Car car1 = new Car(70, 0, 20, -20, new Location(1400, 130+35*i, 180), map.getPaths().get(i),0, CarShape.getShape((i+2)%3+1));
            cars.add(car1);
            new Thread(car1).start();
            map.getPaths().get(i).getVehicles().add(car1);
            root.getChildren().add(car1.getShape());
        }
        for (int i=0;i<3;i++) {
            ArrayList<Car> cars = new ArrayList<>();
            Car car1 = new Car(80, -10, 30, -20, new Location(1200, 130+35*i, 180), map.getPaths().get(i),0, CarShape.getShape((i+3)%3+1));
            cars.add(car1);
            new Thread(car1).start();
            map.getPaths().get(i).getVehicles().add(car1);
            root.getChildren().add(car1.getShape());
        }


        //root.getChildren().add(CarShape.getShape(1));
        //root.getChildren().add(MiniCarShape.getShape(2));
        //root.getChildren().add(TruckShape.getShape(1));
        //root.getChildren().add(MotorcycleShape.getShape(1));



    }
}
*/