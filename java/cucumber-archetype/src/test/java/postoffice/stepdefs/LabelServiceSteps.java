package postoffice.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import postoffice.models.Package;
import postoffice.models.DeliveryType;
import postoffice.services.LabelService;
import postoffice.models.Label;
import java.util.UUID;

public class LabelServiceSteps {

    private Package pkg;
    private LabelService labelService = new LabelService();
    private Label label;

    @Given("a package with dimensions {int} height, {int} width, {int} length, and weight {double} kg, and delivery type {string}")
    public void givenAPackageWithDimensions(int height, int width, int length, double weight, String deliveryType) {
        pkg = new Package();  // Use the default constructor
        
        pkg.setId(UUID.randomUUID());
        pkg.setHeight(height);
        pkg.setWidth(width);
        pkg.setLength(length);
        pkg.setWeight(weight);
        pkg.setDeliveryType(DeliveryType.valueOf(deliveryType.toUpperCase()));  // Convert the string to an enum
    }

    @When("the label is created for the package")
    public void whenTheLabelIsCreated() {
        try {
            label = labelService.createLabel(pkg);
        } catch (IllegalArgumentException e) {
            label = null;
        }
    }

    @Then("the label should be created successfully")
    public void thenTheLabelShouldBeCreated() {
        assertNotNull("Label should be created", label);
    }

    @Then("the shipping cost should be calculated correctly")
    public void thenTheShippingCostShouldBeCalculated() {
        double expectedCost = labelService.calculateShippingCost(pkg);

        // 0.01 is the delta and specifies the allowable margin of error for comparing two floating-point values.
        assertEquals("Shipping cost should match", expectedCost, label.getTotalCost(), 0.01);
    }

    @Then("an exception should be thrown due to invalid package dimensions")
    public void thenAnExceptionShouldBeThrownDueToInvalidDimensions() {
        assertNull("Label should not be created due to invalid dimensions", label);
    }

    @Then("an exception should be thrown due to invalid package weight")
    public void thenAnExceptionShouldBeThrownDueToInvalidWeight() {
        assertNull("Label should not be created due to invalid weight", label);
    }
}