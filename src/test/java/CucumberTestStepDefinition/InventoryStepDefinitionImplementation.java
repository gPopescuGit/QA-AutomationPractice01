package CucumberTestStepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjectsPackage.InventoryPage;
import pageObjectsPackage.LandingPage;
import testComponents.BaseTest;

public class InventoryStepDefinitionImplementation extends BaseTest {

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
		// product Sauce Labs Backpack is not added
		inventoryPage.addProductToCart("Sauce Labs Backpack");
		expectedProductsInCart++;
	}

	@When("I remove one existing product from cart")
	public void I_remove_one_existing_product_from_cart() {
		// Precondition: add a product to cart
		I_add_a_new_product_to_cart();
		// Action
		inventoryPage.removeProductFromCart("Sauce Labs Backpack");
		// Reaction
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

	@Then("Products are ordered according to active filter")
	public void products_are_ordered_according_to_active_filter() {
		Assert.assertTrue(inventoryPage.productsOrderedAccordingToActiveFilter());
	}

	List<String> openedWebsites;

	@When("I click on social hyperlink")
	public void i_press_on_hyperlink() throws Exception {
		openedWebsites = inventoryPage.pressOnSocialHyperlinks();
	}

	@Then("Corresponding page is opened")
	public void corresponding_page_is_opened() {
		Assert.assertTrue(inventoryPage.correspondingPageOpened(openedWebsites));
	}

	@Then("Soft check on hyperlinks indicates response code value under 400")
	public void soft_check_indicates_response_value_under_400() throws Exception {
		Assert.assertTrue(inventoryPage.softCheckSocialHyperlinks());
	}

	@Given("I click on hamburger button icon")
	public void given_I_click_on_hamburger_button_icon() {
		inventoryPage.iClickOnHamburgerButtonIcon();
	}

	@Then("Options menu bar is displayed")
	public void options_menu_bar_is_displayed() throws InterruptedException {
		Assert.assertTrue(inventoryPage.menuItemsDisplayed());
	}

	@When("I click on {string} button")
	public void i_click_on_button(String string) {
		switch (string) {
		case "About": {
			inventoryPage.clickOnAboutBtn();
			break;
		}
		case "Logout": {
			inventoryPage.clickOnLogoutBtn();
			break;
		}
		case "Reset App State": {
			inventoryPage.clickOnResetBtn();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + string);
		}
	}
	
	
	@Then("Webpage {string} is opened")
	public boolean webpage_is_opened(String string) {
	    return driver.getPageSource().contains(string);
	}
	
	@Then("Login page is opened")
	public boolean login_page_is_opened() {
		if(driver.findElement(By.id("login-button")).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	@And("At least one product is added to cart")
	public void at_least_one_product_is_added_to_cart(){
		inventoryPage.addRandomProductToCart();
	}
	
	@Then("Cart indicates no items added")
	public void cart_indicates_no_items_added() {
		Assert.assertEquals(
				String.valueOf(driver.findElements(By.cssSelector(".shopping_cart_badge")).size())
				, "0");
	}
	
//	Assert.assertEquals(inventoryPage.howManyProductsInCart(), String.valueOf(expectedProductsInCart));
	
	@And ("Items including previously added ones can be added to cart")
	public void items_can_be_added_to_cart() {
		Assert.assertTrue(inventoryPage.itemCanBeAddedToCart());
	}
	
	@When("I click on product title")
	public void i_click_on_product() {
		inventoryPage.clickonRandomProduct();
	}
	
	@Then ("Corresponding product page is opened")
	public void corresponding_product_page_is_opened() {
		Assert.assertTrue(inventoryPage.correspondingProductPageIsOpened());
		
	}
}
