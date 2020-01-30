package ir.ac.kntu.View;

import ir.ac.kntu.model.Car;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CarShape {
    public static Rectangle getShape(int select) throws FileNotFoundException {
        FileInputStream input = null;
        if (select==1) input=new FileInputStream("src\\main\\Java\\ir\\ac\\kntu\\Image\\Car_Red.png");
        else if (select==2) input=new FileInputStream("src\\main\\Java\\ir\\ac\\kntu\\Image\\Car_Green.png");
        else if (select==3) input=new FileInputStream("src\\main\\Java\\ir\\ac\\kntu\\Image\\Car_Blue.png");
        else if (select==4) input=new FileInputStream("src\\main\\Java\\ir\\ac\\kntu\\Image\\Car_Yellow.png");
        Image image = new Image(input);
        ImagePattern image_pattern = new ImagePattern(image);
        Rectangle rect = new Rectangle(-100, -100, 30, 20);
        rect.setFill(image_pattern);
        return rect;
    }
}
