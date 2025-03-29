Feature: Controlling user addition and deletion


  Background:
    Given user navigates to Admin tab

  Scenario: verify system behavior when a new user is added
    When user click add new
    And enters user data and saves
    Then the number of users should increase

Scenario: verify system behavior when user is deleted
  When user clicks delete user
  And gets users count
  Then the number of users should decrease by one