package postoffice.models;

public class Package {
    private int id;
    private int height;
    private int width;
    private int length;
    private double weight;
    private DeliveryType deliveryType;


    // Constructor
    public Package(int id, int height, int width, int length, double weight, DeliveryType deliveryType) {
        this.id = id;
        this.height = height;
        this.width = width;
        this.length = length;
        this.weight = weight;
        this.deliveryType = deliveryType;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public double getWeight() {
        return weight;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    // Getters and Setters for DeliveryType
    public DeliveryType getDeliveryType() {
        return deliveryType;
     }
    
    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    @Override
    public String toString() {
        return "Package{" +
                "id=" + id +
                ", height=" + height +
                ", width=" + width +
                ", length=" + length +
                ", weight=" + weight +
                ", deliveryType=" + deliveryType +
                '}';
    }
}