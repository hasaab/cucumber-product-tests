#Author: hiqatech@gmail.com
#Keywords Summary : YouTube Search Tests

Feature: YouTube Search Tests

  @YouTubeWebSmoke
  Scenario:YouTubeWeb - I can find and play my music
    Given I navigate to the Home page
    And I "enter" "Dash Berlin - With you" into the "search_field"
    When I "click" the "search_submit"
    And I wait "4" sec/s for "search"
    Then I should see the "search_result1_image"
    And I takes screenshot as "evidence1"
    Then I "select" the "search_result1_title"
    And I wait "5" sec/s for "play"

    #And I have fun :)
