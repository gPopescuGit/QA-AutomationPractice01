
@tag
Feature: Checkout Page
  I want to use this template for my feature file

	Background:
  Given I landed on Checkout page

  @Checkout
  Scenario: User fills in its details and continues to Overview Page
    Given I filled the "First Name" field on Checkout page
    Given I filled the "Last Name" field on Checkout page
    Given I filled the "Zip/Postal Code" field on Checkout page
    When I click on "Continue" button on Checkout page
    Then I am taken to Overview page
    
  @Checkout
  Scenario Outline: User fills information without first name and attempts to checkout
    Given I filled the "Last Name" field on Checkout page
    Given I filled the "Zip/Postal Code" field on Checkout page
    When I click on "Continue" button on Checkout page
    Then I am still on Checkout page
    And Error message "Error: First Name is required" is displayed on Checkout page

  @Checkout
  Scenario Outline: User fills information without last name and attempts to checkout
    Given I filled the "First Name" field on Checkout page
    Given I filled the "Zip/Postal Code" field on Checkout page
    When I click on "Continue" button on Checkout page
    Then I am still on Checkout page
    And Error message "Error: Last Name is required" is displayed on Checkout page
    
  @Checkout
  Scenario Outline: User fills information without last name and attempts to checkout
    Given I filled the "First Name" field on Checkout page
    Given I filled the "Last Name" field on Checkout page
    When I click on "Continue" button on Checkout page
    Then I am still on Checkout page
    And Error message "Error: Postal Code is required" is displayed on Checkout page
    
  @Checkout
  Scenario: From Checkout page User presses 'Cancel'
    When I click on "Cancel" button on Checkout page
    Then I am taken back to Cart page
    
    
    