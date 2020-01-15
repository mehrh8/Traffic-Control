package ir.ac.kntu.model;

import java.util.ArrayList;

public abstract class Path {
    private String id;
    private double maxV;
    private Path next;
    private ArrayList<Vehicle> vehicles;
    private Boolean truck;
    private String nextPath;
    private int nextK;//0->connected 1->end 2->wall

    public int getNextK() {
        return nextK;
    }

    public void setNextK(int nextK) {
        this.nextK = nextK;
    }

    public Path(String id, double maxV, Boolean truck, String nextPath) {
        this.id = id;
        this.maxV = maxV;
        this.vehicles=new ArrayList<>();
        this.truck=truck;
        this.nextPath=nextPath;
    }

    public Path getNext() {
        return next;
    }

    public void setNext(Path next) {
        this.next = next;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Boolean getTruck() {
        return truck;
    }

    public void setTruck(Boolean truck) {
        this.truck = truck;
    }

    public String getNextPath() {
        return nextPath;
    }

    public void setNextPath(String nextPath) {
        this.nextPath = nextPath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getMaxV() {
        return maxV;
    }

    public void setMaxV(double maxV) {
        this.maxV = maxV;
    }

}
