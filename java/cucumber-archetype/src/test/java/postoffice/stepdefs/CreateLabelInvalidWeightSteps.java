package postoffice.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import postoffice.models.Package;
import postoffice.models.DeliveryType;
import postoffice.services.LabelService;

public class CreateLabelInvalidWeightSteps {

    private Package pkg;
    private LabelService labelService = new LabelService();
    private String errorMessage;

    @Given("a package with invalid weight {int} height, {int} width, {int} length, and weight {double} kg, and delivery type {string}")
    public void createLabelInvalidWeight(int height, int width, int length, double weight,
            String deliveryType) {
        pkg = new Package(); // Initialize a new Package object

        pkg.setHeight(height);
        pkg.setWidth(width);
        pkg.setLength(length);
        pkg.setWeight(weight);
        pkg.setDeliveryType(DeliveryType.valueOf(deliveryType.toUpperCase())); // Convert string to DeliveryType enum
    }

    @When("the label is created for the invalid weight package")
    public void invalidWeight_whenTheLabelIsCreated() {
        try {
            labelService.createLabel(pkg);
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage(); // Capture the exception message
        }
    }

    @Then("an exception should be thrown due to {string}")
    public void thenAnExceptionShouldBeThrownDueToInvalidWeight(String expectedMessage) {
        assertEquals(expectedMessage, errorMessage);
    }
}