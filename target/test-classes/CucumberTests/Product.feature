
@tag
Feature: Product page
  I want to use this template for my feature file

	Background:
	Given I landed on a Product page
	#pick randomly

  @Product
  Scenario: Add product to cart from Product page
    When I click on "Add to cart" button
    Then Cart icon indicates correct number of products in the cart
    
  @Product
  Scenario: Remove product from Product page
  	Given Product was added to cart
    When I click on "Add to cart" button
    Then Cart icon indicates correct number of products in the cart
        
  @Product
  Scenario: Product information matches displayed informations from Inventory page
  	Given Product already added to cart
    Then Displayed information matches description from Inventory page
     

