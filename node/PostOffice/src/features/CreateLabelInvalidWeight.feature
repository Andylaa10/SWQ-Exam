Feature: Handling invalid package weight
  Scenario Outline: Package label creation fails due to invalid weight
    Given a package with invalid weight <height> height, <width> width, <length> length, and weight <weight> kg, and delivery type "<deliveryType>"
    When the label is created for the invalid weight package
    Then an exception should be thrown due to "<expectedMessage>"

    Examples:
      | height | width | length | weight | deliveryType | expectedMessage                          |
      | 30     | 30    | 30     | 0.0    | STANDARD     | Package is out of allowable weight range |
      | 30     | 30    | 30     | 30.1   | STANDARD     | Package is out of allowable weight range |