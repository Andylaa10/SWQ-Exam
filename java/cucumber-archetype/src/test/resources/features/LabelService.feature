Feature: Package label creation

  Scenario: Create a valid package label
    Given a package with dimensions 20 height, 20 width, 20 length, and weight 10.0 kg, and delivery type "STANDARD"
    When the label is created for the package
    Then the label should be created successfully
    And the shipping cost should be calculated correctly

  Scenario: Package with invalid dimensions
    Given a package with dimensions 5 height, 5 width, 5 length, and weight 10.0 kg, and delivery type "STANDARD"
    When the label is created for the package
    Then an exception should be thrown due to invalid package dimensions

  Scenario: Package with invalid weight
    Given a package with dimensions 20 height, 20 width, 20 length, and weight 35.0 kg, and delivery type "STANDARD"
    When the label is created for the package
    Then an exception should be thrown due to invalid package weight