Feature: verify login functionality
Scenario: verify login functionality using valid credentials
  Given user navigates to website login page
  When user enters valid credentials
  And click login
  Then user should be navigated to website home page
