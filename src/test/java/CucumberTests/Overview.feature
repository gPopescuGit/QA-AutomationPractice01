@tag
Feature: Overview Page
  I want to use this template for my feature file

	Background:
  Given I landed on Overview page

  @Overview
  Scenario: Title of your scenario
    Given At least one product is visible in Overview page
    Then Item total equals sum of visible products in Overview page

  @Overview
  Scenario Outline: User press 'Cancel' from Overview Page
    Given At least one product is visible in Overview page
    When I click on "Cancel" button on Overview page
    Then From Overview page I am taken to Inventory page

  @Overview
  Scenario Outline: User completes order by pressing 'Finish'
    Given At least one product is visible in Overview page
    When I click on "Finish" button on Overview page
    Then I am taken to Confirmation page

