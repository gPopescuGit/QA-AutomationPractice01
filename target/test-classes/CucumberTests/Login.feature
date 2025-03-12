@tag
Feature: Login page
  I want to use this template for my feature file
  
  Background:
  Given I landed on ECommercePage
  

  @Login
  Scenario Outline: Valid Login Attempt
    When I log in with username <username> and password <password>
    Then Inventory page is opened

    Examples: 
      | username  		| password 			| 
      | standard_user | secret_sauce 	| 

  @Login @ErrorValidation
  Scenario Outline: Invalid Login Attempt
    When I log in with username <username> and password <password>
    Then Error message "Epic sadface: Username and password do not match any user in this service" is displayed
    And Login Failed

    Examples: 
      | username  		| password 			| 
      | foo_user 			| foo_pass 			|	
      
  @Login @ErrorValidation
  Scenario Outline: Blocked user Login Attempt
    When I log in with username <username> and password <password>
    Then Error message "Epic sadface: Sorry, this user has been locked out." is displayed
    And Login Failed

    Examples: 
      | username  						| password 					| 
      | locked_out_user 			| secret_sauce 			|	

  @Login @ErrorValidation
  Scenario Outline: Attempt to log in without password
    When I log in with username <username> and password <password>
    Then Error message "Epic sadface: Password is required" is displayed
    And Login Failed

    Examples: 
      | username  			| password		|
      | standard_user 	|	<blank>			|
      
  @Login @ErrorValidation
  Scenario Outline: Attempt to log in without username
    When I log in with username <username> and password <password>
    Then Error message "Epic sadface: Username is required" is displayed
    And Login Failed

    Examples: 
      | username  		| password					|
      | <blank> 			|	secret_sauce			|