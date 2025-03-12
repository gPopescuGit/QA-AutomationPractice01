@tag
Feature: Cart Page
  I want to use this template for my feature file


	Background:
  Given I landed on Cart page

  @Cart
  Scenario: Products added are listed in cart
  	Given At least one product was added to cart
    Then Previously added products from Inventory page are visible in cart

  @Cart
  Scenario: Remove product from cart
    Given At least one product was added to cart
    When I click on selected product "Remove" button
    Then Product is removed from Cart
    
  @Cart
  Scenario: Unable to checkout without products in cart
    When I click on "Checkout" button
    Then I am notified that Cart is empty
    And Cart page is still open
    
  @Cart
  Scenario: User clicks on 'continue shopping'
    When I click on "Continue Shopping"
    Then Inventory page is opened  
    
	@Cart
  Scenario: User proceeds to checkout
    Given At least one product was added to cart
    When I click on "Checkout" button
    Then Checkout page is opened
    
    