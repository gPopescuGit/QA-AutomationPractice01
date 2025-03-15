package CucumberTestStepDefinition;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjectsPackage.CartPage;
import pageObjectsPackage.CheckOutPage;
import pageObjectsPackage.InventoryPage;
import pageObjectsPackage.LandingPage;
import pageObjectsPackage.OverviewPage;
import testComponents.BaseTest;

public class OverviewStepDefinitionImplementation extends BaseTest {

	LandingPage landingPage;
	InventoryPage inventoryPage;
	CheckOutPage checkOutPage;
	CartPage cartPage;
	OverviewPage overviewPage; 
	
	@Given ("I landed on Overview page")
	public void i_landed_on_checkout_page() throws IOException {
		landingPage = launchApplication();
		inventoryPage = landingPage.loginApplication("standard_user", "secret_sauce");
		inventoryPage.addProductToCart("Sauce Labs Backpack");
		cartPage = inventoryPage.goToCartPage();
		checkOutPage = cartPage.goToCheckout();
		overviewPage = checkOutPage.completeCheckout("fname", "lname", "postalcode");
	}
	
	@Given ("At least one product is visible in Overview page")
	public void at_least_one_product_is_visible_in_overview_page() {
		Assert.assertTrue(overviewPage.productsVisibleInOverviewPage()>0);
	}
	
	@Then ("Item total equals sum of visible products in Overview page")
	public void item_total_equals_sum_of_visible_products_in_overview_page() {
		Assert.assertTrue(overviewPage.totalValueWithoutTaxMatchesSumOfProductPrices());
	}
	
	@When("I click on {string} button on Overview page")
	public void i_click_on_button_on_overview_page(String button) {
		overviewPage.iClickOnButtonFromOverviewPage(button);
	}
	
	@Then ("From Overview page I am taken to Inventory page")
	public void from_overview_page_i_am_taken_to_inventory_page() {
		Assert.assertTrue(expected_opened_webpage("inventory"));
	}
	
	@Then ("I am taken to Confirmation page")
	public void i_am_taken_to_confirmation_page() {
		Assert.assertTrue(expected_opened_webpage("confirmation"));
	}
	
}
