package CucumberTestStepDefinition;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjectsPackage.CartPage;
import pageObjectsPackage.CheckOutPage;
import pageObjectsPackage.ConfirmationPage;
import pageObjectsPackage.InventoryPage;
import pageObjectsPackage.LandingPage;
import pageObjectsPackage.OverviewPage;
import testComponents.BaseTest;

public class ConfirmationStepDefinitionImplementation extends BaseTest  {

	LandingPage landingPage;
	InventoryPage inventoryPage;
	CheckOutPage checkOutPage;
	CartPage cartPage;
	OverviewPage overviewPage; 
	ConfirmationPage confirmationPage;
	
	@Given ("I reached Confirmation page")
	public void i_landed_on_checkout_page() throws IOException {
		landingPage = launchApplication();
		inventoryPage = landingPage.loginApplication("standard_user", "secret_sauce");
		inventoryPage.addProductToCart("Sauce Labs Backpack");
		cartPage = inventoryPage.goToCartPage();
		checkOutPage = cartPage.goToCheckout();
		overviewPage = checkOutPage.completeCheckout("fname", "lname", "postalcode");
		confirmationPage = overviewPage.finishOverview();
	}
	
	@When("I click on {string} button on Confirmation Page")
	public void i_click_on_button_on_confirmation_page(String string) {
		confirmationPage.getBackToProducts(string);
	}
	
	@Then("From Confirmation page I am taken to Inventory page")
	public void from_confirmation_page_i_am_taken_to_inventory_page() {
		Assert.assertTrue(expected_opened_webpage("inventory"));
	}
	
	@Then ("Message {string} is displayed on Confirmation page")
	public void message_is_displayed_on_confirmation_page(String actualMessage) {
		String expectedMessage = "Thank you for your order!";
		Assert.assertEquals(actualMessage, expectedMessage);
	}
}
