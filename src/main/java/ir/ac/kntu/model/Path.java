package ir.ac.kntu.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TimerTask;

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

    public abstract Location getNextLocation(Location nowLocation,double distance);

    public abstract double calcDistance(Location location1,Location location2);

    public abstract Location getStartLocation();

    public abstract Location getEndLocation();

    public void sortVehicles() {
        for (int i = 0; i < vehicles.size(); i++) {
            for (int j = i; j < vehicles.size(); j++) {
                if (vehicles.get(i).compareToByDistanceInNowPath(vehicles.get(j))>0){
                    Vehicle temp=vehicles.get(i);
                    vehicles.set(i,vehicles.get(j));
                    vehicles.set(j,temp);
                }
            }
        }
    }

    public int getIndexVehicle(Vehicle vehicle){
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicle==vehicles.get(i)) return i;
        }
        return -1;
    }
}
