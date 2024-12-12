## WEIGHT (7 test cases)
Feature: Handling invalid package weight
  Scenario: Package label creation fails due to invalid weight
    Given a package with dimensions <height> height, <width> width, <length> length, and weight <weight> kg, and delivery type "<deliveryType>"
    When the label is created for the package
    Then an exception should be thrown due to invalid package weight

    Examples:
      | height | width | length | weight | deliveryType |
      | 30     | 30    | 30     | 0.0    | STANDARD     |
      | 30     | 30    | 30     | 30.1   | STANDARD     |


Feature: Creating valid package labels and calculating shipping cost
  Scenario: Create a valid package label with varying weights
    Given a package with dimensions <height> height, <width> width, <length> length, and weight <weight> kg, and delivery type "<deliveryType>"
    When the label is created for the package
    Then the label should be created successfully
    And the shipping cost should be calculated correctly

    ## Testing: Minimum boundary, Light weight, Medium weight, Heavy weight, Maximum boundary
    Examples:
      | height | width | length | weight | deliveryType |
      | 30     | 30    | 30     | 0.01   | STANDARD     |
      | 30     | 30    | 30     | 2.5    | STANDARD     |
      | 30     | 30    | 30     | 10.0   | STANDARD     |
      | 30     | 30    | 30     | 22.5   | STANDARD     |
      | 40     | 40    | 40     | 30.0   | STANDARD     |

## SIZE (5 test cases)
Feature: Handling invalid package dimensions
  Scenario: Package label creation fails due to invalid dimensions
    Given a package with dimensions <height> height, <width> width, <length> length, and weight <weight> kg, and delivery type "<deliveryType>"
    When the label is created for the package
    Then an exception should be thrown due to invalid package dimensions

    Examples:
      | height | width | length | weight | deliveryType |
      | 5      | 5     | 5      | 10.0   | STANDARD     |
      | 51     | 51    | 51     | 10.0   | ECONOMY      |


Feature: Handling valid package dimensions
  Scenario: Package label creation and shipping cost calculation
    Given a package with the dimensions <height> height, <width> width, <length> length, and a weight <weight> kg, and delivery type "<deliveryType>"
    When the label is created for the package
    Then the label should be created successfully
    And the shipping cost should be calculated correctly

    ## Testing: Minimum boundary, an average value, Maximum boundary
    Examples:
      | height | width | length | weight | deliveryType |
      | 10     | 10    | 5      | 10.0   | STANDARD     |
      | 25     | 25    | 25     | 10.0   | STANDARD     |
      | 50     | 50    | 50     | 10.0   | STANDARD     | 