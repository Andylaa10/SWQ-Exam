Feature: Package validation, label creation and shipping cost calculation

Scenario: Create a valid package label with medium weight
    Given a package with dimensions 30 height, 30 width, 30 length, and weight 10.0 kg, and delivery type "STANDARD"
    When the label is created for the package
    Then the label should be created successfully
    And the shipping cost should be calculated correctly

Scenario: Create a valid package label with heavy weight
    Given a package with dimensions 40 height, 40 width, 40 length, and weight 20.0 kg, and delivery type "EXPRESS"
    When the label is created for the package
    Then the label should be created successfully
    And the shipping cost should be calculated correctly
