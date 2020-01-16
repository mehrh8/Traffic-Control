package ir.ac.kntu.View;

import ir.ac.kntu.model.Car;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TruckShape {
    public static Rectangle getShape(int select) throws FileNotFoundException {
        FileInputStream input = null;
        if (select==1) input=new FileInputStream("src\\main\\Java\\ir\\ac\\kntu\\Image\\Truck_Red.png");
        else if (select==2) input=new FileInputStream("src\\main\\Java\\ir\\ac\\kntu\\Image\\Truck_Green.png");
        else if (select==3) input=new FileInputStream("src\\main\\Java\\ir\\ac\\kntu\\Image\\Truck_Blue.png");
        else if (select==4) input=new FileInputStream("src\\main\\Java\\ir\\ac\\kntu\\Image\\Truck_Yellow.png");
        Image image = new Image(input);
        ImagePattern image_pattern = new ImagePattern(image);
        Rectangle rect = new Rectangle(0, 0, 45, 30);
        rect.setFill(image_pattern);
        return rect;
    }
}
