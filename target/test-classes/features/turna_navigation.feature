Feature: Turna.com Navigation Test

  Scenario: Navigate through different sections of Turna.com
    Given I am on the Turna.com homepage
    When I close the advertisement
    Then I click on the Flight button
    And I click on the Hotel button
    And I click on the Bus button
    And I click on the Car Rental button
    And I click on the Ferry button
