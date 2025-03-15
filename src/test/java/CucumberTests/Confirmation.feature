
@tag
Feature: Confirmation page
  I want to use this template for my feature file

	Background:
  Given I reached Confirmation page
  
  @Confirmation
  Scenario: User presses 'Back to Home' from Confirmation Page
    When I click on "Back to Home" button on Confirmation Page
    Then From Confirmation page I am taken to Inventory page

  @Confirmation
  Scenario Outline: Confirmation message displayed when user reaches Confirmation page
    Then Message "Thank you for your order!" is displayed on Confirmation page

