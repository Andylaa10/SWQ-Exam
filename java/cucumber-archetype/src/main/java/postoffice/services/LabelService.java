package postoffice.services;

import postoffice.models.DeliveryType;
import postoffice.models.Label;
import postoffice.models.Package;

import java.util.UUID;

public class LabelService {

    // Method to validate package dimensions and weight
    private boolean validatePackage(Package pkg) {
        if (pkg.getHeight() < 10 || pkg.getWidth() < 10 || pkg.getLength() < 5 ||
            pkg.getHeight() > 50 || pkg.getWidth() > 50 || pkg.getLength() > 50) {
            System.out.println("Error: Package dimensions are not within the allowed range.");
            return false;
        }

        if (pkg.getWeight() < 0.1 || pkg.getWeight() > 30) {
            System.out.println("Error: Package weight is not within the allowed range.");
            return false;
        }

        return true;
    }

    // Method to calculate shipping cost
    private double calculateShippingCost(Package pkg) {
        double baseCost;

        // Determine base cost based on delivery type
        switch (pkg.deliveryType) {
            case STANDARD:
                baseCost = 60.0;
                break;
            case EXPRESS:
                baseCost = 120.0;
                break;
            case ECONOMY:
                baseCost = 30.0;
                break;
            default:
                throw new IllegalArgumentException("Invalid delivery type.");
        }

        // Add weight-based fees
        if (pkg.getWeight() > 5.0 && pkg.getWeight() <= 15.0) {
            baseCost += 15.0; // Medium weight fee
        } else if (pkg.getWeight() > 15.0 && pkg.getWeight() <= 30.0) {
            baseCost += 30.0; // Heavy weight fee
        }

        return baseCost;
    }

    // Method to create a label
    public Label createLabel(Package pkg) {
        // Validate the package
        if (!validatePackage(pkg)) {
            throw new IllegalArgumentException("Invalid package dimensions or weight.");
        }

        // Calculate the shipping cost
        double shippingCost = calculateShippingCost(pkg);

        // Create the label
        Label label = new Label(0, pkg);
        label.setId(UUID.randomUUID());
        label.setTotalCost(shippingCost)
        System.out.println("Label created successfully!");
        System.out.println("Cost: " + shippingCost);
        System.out.println("Tracking ID: " + trackingNumber);

        return label;
    }
}