Feature: Creating valid package dimensions

  Scenario Outline: Package label creation
    Given a package with valid dimensions <height> height, <width> width, <length> length, and weight <weight> kg, and delivery type "<deliveryType>"
    When the label is created for the valid dimensions package
    Then the label for valid dimensions should be created successfully

    Examples:
      | height | width | length | weight | deliveryType |
      | 10     | 10    | 5      | 10.0   | STANDARD     |
      | 25     | 25    | 25     | 10.0   | STANDARD     |
      | 50     | 50    | 50     | 10.0   | STANDARD     |
