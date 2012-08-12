Feature: Login
  
  Scenario: The user successfully logs in
    Given I am on the login page
    When I enter danix as username
    And I enter danix as password
    And I press the Login to Web Example button
    Then I should see Logout