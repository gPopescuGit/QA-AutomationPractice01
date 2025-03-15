
@tag
Feature: Checkout Page
  I want to use this template for my feature file

	Background:
  Given I landed on Checkout page

  @Checkout
  Scenario: User fills in its details and continues to Overview Page
    Given I filled the "First Name" field
    Given I filled the "Last Name" field
    Given I filled the "Zip/Postal Code" field
    When I click on Continue button
    Then Overview page is open
    
  @Checkout
  Scenario Outline: User fills information without first name and attempts to checkout
    Given I filled the "Last Name" field
    Given I filled the "Zip/Postal Code" field
    When I click on Continue button
    Then Overview page is NOT open
    And Error message "Error: First Name is required" is displayed

  @Checkout
  Scenario Outline: User fills information without last name and attempts to checkout
    Given I filled the "First Name" field
    Given I filled the "Zip/Postal Code" field
    When I click on Continue button
    Then Overview page is NOT open
    And Error message "Error: Last Name is required" is displayed
    
  @Checkout
  Scenario Outline: User fills information without last name and attempts to checkout
    Given I filled the "First Name" field
    Given I filled the "Last Name" field
    When I click on Continue button
    Then Overview page is NOT open
    And Error message "Error: Postal Code is required" is displayed	
    
    
  @Checkout
  Scenario: From Checkout page User presses 'Cancel'
    When I click on Cancel button
    Then Cart page is opened
    
    
    