package postoffice.services;

import postoffice.models.Label;
import postoffice.models.Package;

import java.util.UUID;

public class LabelService {

    // Method to validate package dimensions and weight
    public boolean validatePackage(Package pkg) {
        if (pkg.getHeight() < 10 || pkg.getWidth() < 10 || pkg.getLength() < 5) {
            throw new IllegalArgumentException("Package is below minimum allowable size");
        }

        if (pkg.getHeight() > 50 || pkg.getWidth() > 50 || pkg.getLength() > 50) {
            throw new IllegalArgumentException("Package is above maximum allowable size");
        }

        if (pkg.getWeight() < 0.01 || pkg.getWeight() > 30) {
            throw new IllegalArgumentException("Package is out of allowable weight range");
        }

        return true;
    }

    // Method to calculate shipping cost
    public double calculateShippingCost(Package pkg) {
        double baseCost = 0;

        // Determine base cost based on delivery type
        switch (pkg.getDeliveryType()) {
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
        try {
            validatePackage(pkg); // Ensure validation is done before proceeding
        } catch (IllegalArgumentException e) {
            // Rethrow with the specific message so it's easier for the test to match
            throw new IllegalArgumentException(e.getMessage());
        }

        // Calculate the shipping cost
        double shippingCost = calculateShippingCost(pkg);

        // Generate random UUID
        UUID id = UUID.randomUUID();

        // Create the label
        Label label = new Label(id, pkg, shippingCost);
        label.setTotalCost(shippingCost);

        return label;
    }
}