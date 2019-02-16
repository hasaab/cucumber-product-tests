#Author: hiqatech@gmail.com
#Keywords Summary : Web Tests

Feature: LinkedIn Web Tests

  @Smoke @Chrome @LinkedInWeb
  Scenario: I can login and logout on LinkedIn
    Given I navigate to the "https://ie.linkedin.com/" page
    And I am on the "SignIn" page
    And I enter "kiszols@yahoo.com" as the "email_entry" element
    And I enter "Stridentb52" as the "password_entry" element
    When I select the "signin_button" element
    And I am on the "Main" page
    Then I should see the "user_profile_image" element
    And I select the "user_menu_dropdown" element
    When I select the "user_menu_sign_out_button" element
    And I am on the "SignOut" page
    Then I should not see the "signed_out_message" element