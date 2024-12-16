Feature: Shipping cost calculation based on delivery type

  Scenario Outline: Calculate shipping cost for a valid package with different delivery types
    Given a package with dimensions for shipping <height> height, <width> width, <length> length, and weight <weight> kg, and delivery type "<deliveryType>"
    When the shipping cost is calculated for the package
    Then the shipping cost should be <expectedCost>


    Examples:
      | height | width | length | weight | deliveryType | expectedCost |
      | 30     | 30    | 30     | 4.0    | STANDARD     | 60.0         |
      | 30     | 30    | 30     | 4.0    | EXPRESS      | 120.0        |
      | 30     | 30    | 30     | 4.0    | ECONOMY      | 30.0         |
      | 30     | 30    | 30     | 10.0   | STANDARD     | 75.0         |
      | 30     | 30    | 30     | 10.0   | EXPRESS      | 135.0        |
      | 30     | 30    | 30     | 10.0   | ECONOMY      | 45.0         |
      | 30     | 30    | 30     | 20.0   | STANDARD     | 90.0         |
      | 30     | 30    | 30     | 20.0   | EXPRESS      | 150.0        |
      | 30     | 30    | 30     | 20.0   | ECONOMY      | 60.0         |