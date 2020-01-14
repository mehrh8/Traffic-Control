package ir.ac.kntu.model;

public abstract class Path {
    private String id;
    private double maxV;

    public Path(String id, double maxV) {
        this.id = id;
        this.maxV = maxV;
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
