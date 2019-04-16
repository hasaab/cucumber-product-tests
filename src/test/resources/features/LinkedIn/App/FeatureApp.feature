#Author: hiqatech@gmail.com
#Keywords Summary : Appium Tests

Feature: Android Calculator Plus Tests

  @LinkedinAppSmoke
  Scenario Outline:LinkedinApp I can execute simple 2 operand calculations with correct result
    Given I select the "<operand1>" element
    And I select the "<arithmetic>" element
    And I select the "<string>" element
    When I select the "=" element
    Then The "screen" value should be "<result>"

    Examples:
    |operand1|arithmetic|operand2|result|
    |80      |+         |20      |100   |
    |80      |-         |20      |60    |
    |80      |*         |20      |1600  |
    |80      |/         |20      |4     |