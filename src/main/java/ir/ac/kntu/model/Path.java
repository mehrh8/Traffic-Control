package ir.ac.kntu.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TimerTask;

public abstract class Path {
    private String id;
    private double maxV;
    private Path next;
    private Path previous;
    private ArrayList<Vehicle> vehicles;
    private Boolean truck;
    private String nextPath;
    private String previousPath;
    private int nextK;//0->connected 1->end 2->wall

    public Path getPrevious() {
        return previous;
    }

    public void setPrevious(Path previous) {
        this.previous = previous;
    }

    public int getNextK() {
        return nextK;
    }

    public void setNextK(int nextK) {
        this.nextK = nextK;
    }

    public String getPreviousPath() {
        return previousPath;
    }

    public void setPreviousPath(String previousPath) {
        this.previousPath = previousPath;
    }

    public Path(String id, double maxV, Boolean truck,String previousPath, String nextPath) {
        this.id = id;
        this.maxV = maxV;
        this.vehicles = new ArrayList<>();
        this.truck = truck;
        this.nextPath = nextPath;
        this.previousPath=previousPath;
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

    public abstract Location getNextLocation(Location nowLocation, double distance);

    public abstract double calcDistance(Location location1, Location location2);

    public abstract Location getStartLocation();

    public abstract Location getEndLocation();

    public synchronized void sortVehicles() {
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i) == null) continue;
            for (int j = i + 1; j < vehicles.size(); j++) {
                if (i < vehicles.size() && j < vehicles.size()) {
                    if (vehicles.get(j) == null || vehicles.get(i) == null) continue;
                    if (i < vehicles.size() && j < vehicles.size() && vehicles.get(j) != null && vehicles.get(i) != null && vehicles.get(i).compareToByDistanceInNowPath(vehicles.get(j)) > 0) {
//                        Vehicle tempJ = vehicles.get(j);
//                        Vehicle tempI = vehicles.get(i);
//                        vehicles.remove(i);
//                        vehicles.add(i, tempJ);
//                        vehicles.remove(j);
//                        vehicles.add(j, tempI);
                        Vehicle temp = vehicles.get(i);
                        vehicles.set(i, vehicles.get(j));
                        vehicles.set(j, temp);

                    }

                }
            }
        }
    }

    public int getIndexVehicle(Vehicle vehicle) {
        synchronized (getVehicles()) {
            for (int i = 0; i < vehicles.size(); i++) {
                if(vehicles.get(i) == null) continue;
                if (vehicle.getId() == vehicles.get(i).getId()) return i;
            }
        }
        return -1;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public Vehicle findFront(Location location) {
        synchronized (getVehicles()) {
            for (int i = 0; i < getVehicles().size(); i++) {
                if (getVehicles().get(i) == null) {
                    continue;
                }
                if (calcDistance(getVehicles().get(i).getLocation(), this.getStartLocation()) - calcDistance(location, this.getStartLocation()) > 0)
                    return getVehicles().get(i);
            }
            if (next!=null){
                return next.findFront(next.getStartLocation());
            }
        }
        return null;
    }
    public double findFrontDistance(Location location){
        synchronized (getVehicles()) {
            for (int i = 0; i < getVehicles().size(); i++) {
                if (getVehicles().get(i) == null) {
                    continue;
                }
                if (calcDistance(getVehicles().get(i).getLocation(), this.getStartLocation()) - calcDistance(location, this.getStartLocation()) > 0)
                    return calcDistance(getVehicles().get(i).getLocation(), this.getStartLocation()) - calcDistance(location, this.getStartLocation());
            }
            if (next!=null){
                return calcDistance(this.getEndLocation(),location)+next.findFrontDistance(next.getStartLocation());
            }
        }
        return 0;
    }
    public Vehicle findBack(Location location) {
        synchronized (getVehicles()) {
            for (int i = getVehicles().size() - 1; i > -1; i--) {
                if (getVehicles().get(i) == null) {
                    continue;
                }
                if (calcDistance(getVehicles().get(i).getLocation(), this.getEndLocation()) - calcDistance(location, this.getEndLocation()) > 0)
                    return getVehicles().get(i);
            }
            if (previous!=null){
                return previous.findBack(previous.getEndLocation());
            }
        }
        return null;
    }
    public double findBackDistance(Location location){
        synchronized (getVehicles()) {
            for (int i = getVehicles().size() - 1; i > -1; i--) {
                if (getVehicles().get(i) == null) {
                    continue;
                }
                if (calcDistance(getVehicles().get(i).getLocation(), this.getEndLocation()) - calcDistance(location, this.getEndLocation()) > 0)
                    return calcDistance(getVehicles().get(i).getLocation(), this.getEndLocation()) - calcDistance(location, this.getEndLocation());
            }
            if (previous!=null){
                return calcDistance(location,getStartLocation())+previous.findBackDistance(previous.getEndLocation());
            }
        }
        return 0;
    }


    public abstract Location findLocationInCurrnetPathRight(Location location);
    public abstract Location findLocationInCurrnetPathLeft(Location location);

}
