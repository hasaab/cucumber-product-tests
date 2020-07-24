#Author: hiqatech@gmail.com
#Keywords Summary : YouTube Home Tests

Feature: YouTube Home Tests

  @YouTubeWebRegression
  Scenario:YouTubeWeb - I can find videos with Zurich
    Given I navigate to the Home page
    And I "type" "Zurich" into the "search_field"
    When I "click" the "search_submit"
    And I wait "4" sec/s for "search"
    And I am on the "Search" page
    Then I should see the "search_result_1st"
    And I takes screenshot as "search_evidence_1"

