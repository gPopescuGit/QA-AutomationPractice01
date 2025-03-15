package CucumberTestStepDefinition;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjectsPackage.CartPage;
import pageObjectsPackage.CheckOutPage;
import pageObjectsPackage.InventoryPage;
import pageObjectsPackage.LandingPage;
import testComponents.BaseTest;

public class CheckoutStepDefinitionImplementation extends BaseTest {

	LandingPage landingPage;
	InventoryPage inventoryPage;
	CheckOutPage checkOutPage;
	CartPage cartPage;
	
	@Given ("I landed on Checkout page")
	public void i_landed_on_checkout_page() throws IOException {
		landingPage = launchApplication();
		inventoryPage = landingPage.loginApplication("standard_user", "secret_sauce");
		inventoryPage.addProductToCart("Sauce Labs Backpack");
		cartPage = inventoryPage.goToCartPage();
		checkOutPage = cartPage.goToCheckout();
	}
	
	@Given("I filled the {string} field")
	public void i_filled_the_field(String string) {
		switch (string) {
		case "First Name": {
			checkOutPage.fillFirstName(string);
			break;
		}
		case "Last Name": {
			checkOutPage.fillLastName(string);
			break;
		}
		case "Zip/Postal Code": {
			checkOutPage.fillPostalCode(string);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + string);
		}
	}
	
	@When("I click on Continue button")
	public void checkout_i_click_on_continue_button() {
		checkOutPage.clickOnContinue();
	}

	@Then("Overview page is open")
	public void checkout_overview_page_is_open() {
		//TODO: refactor in Abstract component or base test a method to return title of current page
	}

	@Then("Overview page is NOT open")
	public void checkout_overview_page_is_NOT_open() {
		//TODO: refactor in Abstract component or base test a method to return title of current page
	}
	
	@And("Error message {string} is displayed")
	public void error_message_is_displayed(String string) {
		String errorMessageDisplayed = checkOutPage.errorMessageDisplayed();
		Assert.assertEquals(errorMessageDisplayed, string);
	}
}
