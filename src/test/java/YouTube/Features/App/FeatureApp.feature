#Author: hiqatech@gmail.com
#Keywords Summary : Appium Tests

Feature: Android Calculator Plus Tests

  Background:
    Given I am testing the "CalculatorPlus" product
    And I am on the "Main" page


  @ANDROID @Smoke
  Scenario Outline: I can execute simple 2 operand calculations with correct result
    Given I select the "<operand1>" element -App
    And I select the "<arithmetic>" element -App
    And I select the "<string>" element -App
    When I select the "=" element -App
    Then The "screen" value should be "<result>" -App

    Examples:
    |operand1|arithmetic|operand2|result|
    |80      |+         |20      |100   |
    |80      |-         |20      |60    |
    |80      |*         |20      |1600  |
    |80      |/         |20      |4     |