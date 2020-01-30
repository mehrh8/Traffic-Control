package ir.ac.kntu.View;

import ir.ac.kntu.Presenter.Presenter;
import ir.ac.kntu.model.Car;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class View extends Application {

    private Group root=new Group();
    private Scene scene;
    private View thisObject=this;
    public  static ArrayList<Shape> shapes=new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(root, 1600, 1000, new ImagePattern(new Image(new FileInputStream("src\\main\\Java\\ir\\ac\\kntu\\Image\\grass.png"))));
        stage.setScene(scene);
        stage.setTitle("Traffic");
        stage.setResizable(false);
        stage.show();
        Presenter presenter = new Presenter(root, scene);
        presenter.run();
    }
}
