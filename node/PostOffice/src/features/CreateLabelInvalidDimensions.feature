Feature: Handling invalid package dimensions

  Scenario Outline: Package label creation fails due to invalid dimensions
    Given a package with invalid dimensions <height> height, <width> width, <length> length, and weight <weight> kg, and delivery type "<deliveryType>"
    When the label is created for the invalid dimensions package
    Then an exception should be thrown with "<expectedMessage>"

    Examples:
      | height | width | length | weight | deliveryType | expectedMessage                         |
      | 5      | 5     | 5      | 10.0   | STANDARD     | Package is below minimum allowable size |
      | 51     | 51    | 51     | 10.0   | ECONOMY      | Package is above maximum allowable size |