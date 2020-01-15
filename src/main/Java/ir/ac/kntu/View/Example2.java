package ir.ac.kntu.View;

import ir.ac.kntu.model.Line;
import ir.ac.kntu.model.Map;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Example2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        Group root = new Group();
        Scene scene = new Scene(root, 1650,1000, Color.GREEN);
        stage.setScene(scene);
        stage.setTitle("Test");
        stage.setResizable(false);
        stage.show();
        Map map=new Map();
        map.read("src\\main\\Java\\ir\\ac\\kntu\\Maps\\Map1.txt");
        for (int i= 0; i < 6; i++) {
            LineShape.getShape(root,(Line)map.getPaths().get(i));
        }

    }
}
