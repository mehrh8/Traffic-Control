package ir.ac.kntu.View;

import ir.ac.kntu.model.Curve;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;

public class CurveShape {
    public static void makeCurve(Group root, Curve curve){
        double radius = curve.getRadius();
        double startTeta=curve.getStartTeta();
        double lengthTeta=curve.getLengthTeta();
        double centerX=curve.getCenterX();
        double centerY=curve.getCenterY();
        Arc cruveShape =new Arc(centerX,centerY,radius,radius,startTeta,lengthTeta);
        cruveShape.setFill(null);
        cruveShape.setStroke(Color.GRAY);
        cruveShape.setStrokeWidth(30);
        root.getChildren().add(cruveShape);
        if (curve.getRightCurve()==null){
            double radius2;
            if (lengthTeta>0) radius2=radius+17.5;
            else radius2=radius-17.5;
            Arc whiteRightCurve=new Arc(centerX,centerY,radius2,radius2,startTeta,lengthTeta);
            whiteRightCurve.setFill(null);
            whiteRightCurve.setStroke(Color.WHITE);
            whiteRightCurve.setStrokeWidth(5);
            root.getChildren().add(whiteRightCurve);
        }
        if (curve.getLeftCurve()==null){
            double radius2;
            if (lengthTeta>0) radius2=radius-17.5;
            else radius2=radius+17.5;
            Arc whiteLeftCurve=new Arc(centerX,centerY,radius2,radius2,startTeta,lengthTeta);
            whiteLeftCurve.setFill(null);
            whiteLeftCurve.setStroke(Color.WHITE);
            whiteLeftCurve.setStrokeWidth(5);
            root.getChildren().add(whiteLeftCurve);
        }
        else{
            double radius2;
            if (lengthTeta>0) radius2=radius-17.5;
            else radius2=radius+17.5;
            Arc whiteLeftCurve=new Arc(centerX,centerY,radius2,radius2,startTeta,lengthTeta);
            whiteLeftCurve.setFill(null);
            whiteLeftCurve.setStroke(Color.GRAY);
            whiteLeftCurve.setStrokeWidth(5);
            root.getChildren().add(whiteLeftCurve);

            Arc whiteLeftCurveDash=new Arc(centerX,centerY,radius2,radius2,startTeta,lengthTeta);
            whiteLeftCurveDash.setFill(null);
            whiteLeftCurveDash.setStroke(Color.WHITE);
            whiteLeftCurveDash.getStrokeDashArray().addAll(20d,30d);
            whiteLeftCurveDash.setStrokeWidth(5);
            root.getChildren().add(whiteLeftCurveDash);
        }
    }
}
