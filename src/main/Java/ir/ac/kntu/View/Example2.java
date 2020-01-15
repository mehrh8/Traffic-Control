package ir.ac.kntu.View;

import ir.ac.kntu.model.Map;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Example2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        Group root = new Group();
        Scene scene = new Scene(root, 1500,1000, Color.rgb(0, 0, 10));
        stage.setScene(scene);
        stage.setTitle("Test");
        stage.setResizable(false);
        stage.show();
        Rectangle r = new Rectangle(300,300);
        r.setFill(Color.RED);
        root.getChildren().add(r);
        Map map=new Map();
        map.read("src\\main\\Java\\ir\\ac\\kntu\\Maps\\Map1.txt");
    }
}
