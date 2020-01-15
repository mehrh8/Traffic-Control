package ir.ac.kntu.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
    ArrayList<Path> paths;

    public ArrayList<Path> getPaths() {
        return paths;
    }

    public Map() {
        this.paths = new ArrayList<>();
    }

    public void read(String address) throws FileNotFoundException {
        File myMap = new File(address);
        System.out.println(myMap.getAbsolutePath());
        Scanner mapReader = new Scanner(myMap);
        while (mapReader.hasNextLine()){
            String[] data=mapReader.nextLine().split(" ");
            if (data[0].compareTo("Line")==0){
                String id=data[1];
                double x1=Double.parseDouble(data[2]),y1=Double.parseDouble(data[3]);
                double x2=Double.parseDouble(data[4]),y2=Double.parseDouble(data[5]);
                String leftId=data[6],rightId=data[7];
                double maxV=Double.parseDouble(data[8]);
                String nextId=data[9];
                boolean truck=Integer.parseInt(data[10]) == 1;
                Line line=new Line(id,maxV,truck,nextId,x1,y1,x2,y2,leftId,rightId);
                paths.add(line);
            }
            else if (data[0].compareTo("Curve")==0){
                String id=data[1];
                double centerX=Double.parseDouble(data[2]),centerY=Double.parseDouble(data[3]);
                double radius=Double.parseDouble(data[4]);
                double teta1=Double.parseDouble(data[5]),teta2=Double.parseDouble(data[6]);
                String leftId=data[7],rightId=data[8];
                double maxV=Double.parseDouble(data[9]);
                String nextId=data[10];
                boolean truck=Integer.parseInt(data[11]) == 1;
                Curve curve=new Curve(id,maxV,truck,nextId,centerX,centerY,radius,teta1*Math.PI/180,teta2*Math.PI/180,leftId,rightId);
                paths.add(curve);
            }
        }
        for (int i = 0; i < paths.size(); i++) {
            if (paths.get(i).getNextPath().compareTo("null")==0) {
                paths.get(i).setNext(null);
                paths.get(i).setNextK(2);
            }
            else if (paths.get(i).getNextPath().compareTo("end")==0){
                paths.get(i).setNext(null);
                paths.get(i).setNextK(1);
            }
            else{
                for (int j = 0; j < paths.size(); j++) {
                    if (paths.get(i).getNextPath().compareTo(paths.get(j).getId())==0){
                        paths.get(i).setNext(paths.get(j));
                        paths.get(i).setNextK(0);
                    }
                }
            }
            if (paths.get(i).getClass()==Line.class){
                Line line=(Line) paths.get(i);

                if (line.getLeftId().compareTo("null")==0) line.setLeftLine(null);
                else {
                    for (Path path : paths) {
                        if (line.getLeftId().compareTo(path.getId()) == 0) {
                            line.setLeftLine((Line) path);
                        }
                    }
                }

                if (line.getRightId().compareTo("null")==0) line.setRightLine(null);
                else {
                    for (Path path : paths) {
                        if (line.getRightId().compareTo(path.getId()) == 0) {
                            line.setRightLine((Line) path);
                        }
                    }
                }
            }
            if (paths.get(i).getClass()==Curve.class){
                Curve curve=(Curve) paths.get(i);

                if (curve.getLeftId().compareTo("null")==0) curve.setLeftCurve(null);
                else{
                    for (Path path : paths) {
                        if (curve.getLeftId().compareTo(path.getId()) == 0) {
                            curve.setLeftCurve((Curve) path);
                        }
                    }
                }

                if (curve.getRightId().compareTo("null")==0) curve.setRightCurve(null);
                else{
                    for (Path path : paths) {
                        if (curve.getRightId().compareTo(path.getId()) == 0) {
                            curve.setRightCurve((Curve) path);
                        }
                    }
                }
            }
        }
    }
}
