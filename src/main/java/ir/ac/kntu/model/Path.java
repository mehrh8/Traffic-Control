package ir.ac.kntu.model;

public abstract class Path {
    private String id;
    private double maxV;
    private ESPoint d1,d2;
    private Path next;

    public Path(String id, double maxV, ESPoint d1, ESPoint d2, Path next) {
        this.id = id;
        this.maxV = maxV;
        this.d1 = d1;
        this.d2 = d2;
        this.next = next;
    }

    public ESPoint getD1() {
        return d1;
    }

    public void setD1(ESPoint d1) {
        this.d1 = d1;
    }

    public ESPoint getD2() {
        return d2;
    }

    public void setD2(ESPoint d2) {
        this.d2 = d2;
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
