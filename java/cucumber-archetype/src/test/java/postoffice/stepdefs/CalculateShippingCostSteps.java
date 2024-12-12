package postoffice.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import postoffice.models.Package;
import postoffice.models.DeliveryType;
import postoffice.services.LabelService;

public class CalculateShippingCostSteps {

    private Package pkg;
    private LabelService labelService = new LabelService();
    private double calculatedShippingCost;

    @Given("a package with dimensions for shipping {int} height, {int} width, {int} length, and weight {double} kg, and delivery type {string}")
    public void calculateShippingCostPackage(int height, int width, int length, double weight, String deliveryType) {
        pkg = new Package(); // Initialize a new Package object

        pkg.setHeight(height);
        pkg.setWidth(width);
        pkg.setLength(length);
        pkg.setWeight(weight);
        pkg.setDeliveryType(DeliveryType.valueOf(deliveryType.toUpperCase())); // Convert string to DeliveryType enum
    }

    @When("the shipping cost is calculated for the package")
    public void whenTheShippingCostIsCalculated() {
        calculatedShippingCost = labelService.calculateShippingCost(pkg);
    }

    @Then("the shipping cost should be {double}")
    public void thenTheShippingCostShouldBe(double expectedCost) {
        assertEquals("Shipping cost should match the expected cost", expectedCost, calculatedShippingCost, 0.01);
    }
}