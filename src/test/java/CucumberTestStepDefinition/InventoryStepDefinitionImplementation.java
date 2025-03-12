package CucumberTestStepDefinition;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjectsPackage.InventoryPage;
import pageObjectsPackage.LandingPage;
import testComponents.BaseTest;

public class InventoryStepDefinitionImplementation extends BaseTest{
	
	public LandingPage landingPage;
	public InventoryPage inventoryPage;
	private int expectedProductsInCart = 0;
	
	
	@Given("I landed on Inventory page") 
    public void i_landed_on_Inventory_Page() throws IOException {
		landingPage = launchApplication();
		inventoryPage = landingPage.loginApplication("standard_user", "secret_sauce");

    }
	
	@When("I add a new product to cart")
	public void I_add_a_new_product_to_cart() {
		//product Sauce Labs Backpack is not added 
		inventoryPage.addProductToCart("Sauce Labs Backpack");
		expectedProductsInCart++;
	}
	
	@When("I remove one existing product from cart")
	public void I_remove_one_existing_product_from_cart() {
		//Precondition: add a product to cart
		I_add_a_new_product_to_cart();
		//Action
		inventoryPage.removeProductFromCart("Sauce Labs Backpack");
		//Reaction
		expectedProductsInCart--;
	}

	@Then("Cart icon indicates correct number of products in the cart")
	public void Cart_icon_indicates_correct_number_of_products_in_the_cart() {
		Assert.assertEquals(inventoryPage.howManyProductsInCart(), String.valueOf(expectedProductsInCart));
	}

	String activeFilter;
	
	@When("^Active filter is (.+)$")
	public void active_filter_is_filterName(String filter) {
		inventoryPage.selectFilter(filter);
		activeFilter = inventoryPage.getActiveFilter();
	}
	
	@Then ("Products are ordered according to active filter")
	public void products_are_ordered_according_to_active_filter() {
		Assert.assertTrue(inventoryPage.productsOrderedAccordingToActiveFilter());
	}

	@When("I press on social hyperlink")
	public void i_press_on_hyperlink() throws Exception {
		
//		inventoryPage.pressOnHyperlink();
	}
	
	@Then("Corresponding page is opened")
	public void corresponding_page_is_opened() {
		//TODO: 
	}
	
	@Then("Soft check on hyperlinks indicates response code value under 400")
	public void soft_check_indicates_response_value_under_400() throws Exception {
		inventoryPage.softCheckSocialHyperlinks();
	}
}
