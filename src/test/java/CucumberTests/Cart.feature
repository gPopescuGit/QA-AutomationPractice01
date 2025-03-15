@tag
Feature: Cart Page
  I want to use this template for my feature file

	Background:
  Given I landed on Cart page

  #@Cart
  #Scenario: Products added are listed in cart
  #	Given At least one product was added to cart
    #Then Previously added products from Inventory page are visible in cart

  @Cart
  Scenario: Remove product from cart
    Given At least one product was added to cart
    When I remove one product from cart
    Then Removed product is no longer in Cart
    #
    #bug
  @Cart
  Scenario: Unable to checkout without products in cart
    When I click on "Checkout" button from Cart page
    Then I am notified the Cart is empty and cannot proceed to checkout
    And I am still in cart page
    
  @Cart
  Scenario: User clicks on 'continue shopping'
    When I click on "Continue Shopping" button from Cart page
    Then I am taken back to Inventory page
    
	@Cart
  Scenario: User proceeds to checkout
    Given At least one product was added to cart
    When I click on "Checkout" button from Cart page
    Then I proceed to checkout
    
    