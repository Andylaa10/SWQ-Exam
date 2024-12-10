package postoffice.models;

public class Label {
    private UUID id;
    private Package pkg;
    private double totalCost;

    // Constructor
    public Label(UUID id, Package pkg, double totalCost) {
        this.id = id;
        this.pkg = pkg;
        this.totalCost = totalCost
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public Package getPackage() {
        return pkg;
    }

    public double getTotalCost(){
        return totalCost;
    }

    // Setters
    public void setId(UUID id) {
        this.id = id;
    }

    public void setPackage(Package pkg) {
        this.pkg = pkg;
    }

    public void setTotalCost(double totalCost){
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                "totalCost=" + totalCost +
                ", packageId=" + (pkg != null ? pkg.getId() : "null") +
                '}';
    }
}