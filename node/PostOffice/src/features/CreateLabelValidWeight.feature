Feature: Creating valid package weights
  Scenario Outline: Create a valid package label with varying weights
    Given a package with valid weight <height> height, <width> width, <length> length, and weight <weight> kg, and delivery type "<deliveryType>"
    When the label is created for the valid weight package
    Then the label for valid weight should be created successfully

    ## Testing: Minimum boundary, Light weight, Medium weight, Heavy weight, Maximum boundary
    Examples:
      | height | width | length | weight | deliveryType |
      | 30     | 30    | 30     | 0.01   | STANDARD     |
      | 30     | 30    | 30     | 2.5    | STANDARD     |
      | 30     | 30    | 30     | 10.0   | STANDARD     |
      | 30     | 30    | 30     | 22.5   | STANDARD     |
      | 40     | 40    | 40     | 30.0   | STANDARD     |