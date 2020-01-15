package ir.ac.kntu.View;

import ir.ac.kntu.model.Line;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class LineShape {
    public static void makeShape(Group root,Line line){
        double length = line.getLength();
        double angle=line.getAngle();
        System.out.println(angle);
        javafx.scene.shape.Line lineShape = new javafx.scene.shape.Line(line.getX1(),line.getY1(),line.getX2(),line.getY2());
        lineShape.setStroke(Color.GRAY);
        lineShape.setStrokeWidth(30);
        root.getChildren().add(lineShape);
        System.out.println(angle);
        if (line.getRightLine()==null){
            javafx.scene.shape.Line whiteRightLine=new javafx.scene.shape.Line(line.getX1()+Math.sin(-angle*Math.PI/180)*17.5,line.getY1()+Math.cos(-angle*Math.PI/180)*17.5,line.getX2()+Math.sin(-angle*Math.PI/180)*17.5,line.getY2()+Math.cos(-angle*Math.PI/180)*17.5);
            whiteRightLine.setStroke(Color.WHITE);
            whiteRightLine.setStrokeWidth(5);
            root.getChildren().add(whiteRightLine);
        }
        if (line.getLeftLine()==null){
            javafx.scene.shape.Line whiteLeftLine=new javafx.scene.shape.Line(line.getX1()-Math.sin(-angle*Math.PI/180)*17.5,line.getY1()-Math.cos(-angle*Math.PI/180)*17.5,line.getX2()-Math.sin(-angle*Math.PI/180)*17.5,line.getY2()-Math.cos(-angle*Math.PI/180)*17.5);
            whiteLeftLine.setStroke(Color.WHITE);
            whiteLeftLine.setStrokeWidth(5);
            root.getChildren().add(whiteLeftLine);
        }
        else{
            javafx.scene.shape.Line whiteLeftLineGray = new javafx.scene.shape.Line(line.getX1()-Math.sin(-angle*Math.PI/180)*17.5,line.getY1()-Math.cos(-angle*Math.PI/180)*17.5,line.getX2()-Math.sin(-angle*Math.PI/180)*17.5,line.getY2()-Math.cos(-angle*Math.PI/180)*17.5);
            whiteLeftLineGray.setStroke(Color.GRAY);
            whiteLeftLineGray.setStrokeWidth(5);
            root.getChildren().add(whiteLeftLineGray);

            javafx.scene.shape.Line whiteLeftLineDash = new javafx.scene.shape.Line(line.getX1()-Math.sin(-angle*Math.PI/180)*17.5,line.getY1()-Math.cos(-angle*Math.PI/180)*17.5,line.getX2()-Math.sin(-angle*Math.PI/180)*17.5,line.getY2()-Math.cos(-angle*Math.PI/180)*17.5);
            whiteLeftLineDash.getStrokeDashArray().addAll(20d, 30d);
            whiteLeftLineDash.setStroke(Color.WHITE);
            whiteLeftLineDash.setStrokeWidth(5);
            root.getChildren().add(whiteLeftLineDash);
        }
    }
}
