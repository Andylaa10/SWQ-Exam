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

  Scenario: Package with invalid dimensions (too small)
    Given a package with dimensions 5 height, 5 width, 5 length, and weight 10.0 kg, and delivery type "STANDARD"
    When the label is created for the package
    Then an exception should be thrown due to invalid package dimensions

  Scenario: Package with invalid weight (too large)
    Given a package with dimensions 20 height, 20 width, 20 length, and weight 35.0 kg, and delivery type "ECONOMY"
    When the label is created for the package
    Then an exception should be thrown due to invalid package weight