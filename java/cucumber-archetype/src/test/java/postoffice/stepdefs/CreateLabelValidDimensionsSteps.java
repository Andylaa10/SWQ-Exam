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

public class CreateLabelValidDimensionsSteps {

    private Package pkg;
    private LabelService labelService = new LabelService();
    private Label label;

    @Given("a package with valid dimensions {int} height, {int} width, {int} length, and weight {double} kg, and delivery type {string}")
    public void createLabelValidDimensions(int height, int width, int length, double weight, String deliveryType) {
        pkg = new Package();

        pkg.setId(UUID.randomUUID());
        pkg.setHeight(height);
        pkg.setWidth(width);
        pkg.setLength(length);
        pkg.setWeight(weight);
        pkg.setDeliveryType(DeliveryType.valueOf(deliveryType.toUpperCase())); // Convert string to enum
    }

    @When("the label is created for the valid dimensions package")
    public void validDimensions_whenTheLabelIsCreated() {
        label = labelService.createLabel(pkg);
    }

    @Then("the label for valid dimensions should be created successfully")
    public void validDimensions_thenTheLabelShouldBeCreated() {
        assertNotNull("Label should be created", label);
    }
}
