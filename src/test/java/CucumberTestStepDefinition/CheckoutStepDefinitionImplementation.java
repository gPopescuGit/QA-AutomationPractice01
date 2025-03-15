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
	
	@Given("I filled the {string} field on Checkout page")
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
	
	@When("I click on {string} button on Checkout page")
	public void i_click_on_button_on_checkout_page(String button) {
		checkOutPage.iClickOnButtonFromCheckoutPage(button);
	}


	@Then("I am taken to Overview page")
	public void i_am_taken_to_overview_page() {
		Assert.assertTrue(expected_opened_webpage("overview"));
	}

	@Then("I am still on Checkout page")
	public void i_am_still_on_checkout_page() {
		Assert.assertTrue(expected_opened_webpage("checkout"));
	}
	
	@Then("I am taken back to Cart page")
	public void i_am_taken_back_to_cart_page() {
		Assert.assertTrue(expected_opened_webpage("cart"));
	}
	
	@And("Error message {string} is displayed on Checkout page")
	public void error_message_is_displayed(String string) {
		String errorMessageDisplayed = checkOutPage.errorMessageDisplayed();
		Assert.assertEquals(errorMessageDisplayed, string);
	}
}
