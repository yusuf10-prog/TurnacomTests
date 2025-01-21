@daily_rotation
Feature: Daily Rotation Tests
  Different test scenarios for each day of the week

  @monday
  Scenario: Monday - Check Product Search
    Given user is on homepage
    When user searches for "laptop"
    Then search results should be displayed
    And search results should contain "laptop"

  @tuesday
  Scenario: Tuesday - Check Shopping Cart
    Given user is on homepage
    When user adds first product to cart
    Then shopping cart should be updated
    And shopping cart should contain the product

  @wednesday
  Scenario: Wednesday - Check User Login
    Given user is on login page
    When user enters valid credentials
    Then user should be logged in successfully

  @thursday
  Scenario: Thursday - Check Product Categories
    Given user is on homepage
    When user clicks on any category
    Then category products should be displayed
    And product count should be greater than zero

  @friday
  Scenario: Friday - Check Product Details
    Given user is on homepage
    When user clicks on first product
    Then product details should be displayed
    And product price should be visible

  @saturday
  Scenario: Saturday - Check Filters
    Given user is on homepage
    When user applies price filter
    Then filtered results should be displayed
    And products should match the price filter

  @sunday
  Scenario: Sunday - Check Footer Links
    Given user is on homepage
    When user clicks on each footer link
    Then all links should work correctly
    And each page should load properly
