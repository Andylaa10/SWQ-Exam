package postoffice.models;

public class Label {
    private int id;
    private Package pkg;

    // Constructor
    public Label(int id, Package pkg) {
        this.id = id;
        this.pkg = pkg;
    }

    // Getters
    public int getId() {
        return id;
    }

    public Package getPackage() {
        return pkg;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setPackage(Package pkg) {
        this.pkg = pkg;
    }

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", packageId=" + (pkg != null ? pkg.getId() : "null") +
                '}';
    }
}