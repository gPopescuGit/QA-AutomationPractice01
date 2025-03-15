package CucumberTestStepDefinition;

import java.io.IOException;
import java.util.List;

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

public class CartStepDefinitionImplementation extends BaseTest {

	public LandingPage landingPage;
	public InventoryPage inventoryPage;
	public CartPage cartPage;
	public CheckOutPage checkOutPage;
	
	@Given ("I landed on Cart page")
	public void i_landed_on_cart_page() throws IOException {
		landingPage = launchApplication();
		inventoryPage = landingPage.loginApplication("standard_user", "secret_sauce");
		cartPage = inventoryPage.goToCartPage();
	}
	
	String addedProductInCart = "Sauce Labs Bike Light";
	
	@Given ("At least one product was added to cart")
	public void at_least_one_product_was_added_to_cart() {
		cartPage.continueShopping();
		inventoryPage.addProductToCart(addedProductInCart);
		inventoryPage.goToCartPage();
	}
	
	@Then("Previously added products from Inventory page are visible in cart")
	public void previously_added_products_are_visible_in_cart() {
		List<String> itemsInCart = cartPage.itemsInCart();
		Assert.assertTrue(itemsInCart.contains(addedProductInCart));
	}
	
	@When("I remove one product from cart")
	public void i_remove_one_product_from_cart() {
		cartPage.removeProductFromCart(addedProductInCart);
	}
	
	@Then("Removed product is no longer in Cart")
	public void removed_product_is_no_longer_in_cart() {
		Assert.assertTrue(cartPage.productIsNotInCart(addedProductInCart));
	}
	
	@When("I click on {string} button from Cart page")
	public void i_click_on_checkout_button(String string) {
		cartPage.iClickOnButtonFromCartPage(string);
	}
	
	
	@Then("I am taken back to Inventory page")
	public void i_am_taken_back_to_inventory_page() {
		Assert.assertTrue(expected_opened_webpage("inventory"));
	}
	
	@Then("I am notified the Cart is empty and cannot proceed to checkout")
	public void i_am_notified_the_cart_is_empty_and_cannot_proceed_to_checkout(){
		//there is no notification implemented in webpage
		String expectedErrorMessage = "no message";
		Assert.assertEquals(expectedErrorMessage, "Cart is empty, cannot proceed to checkout");
	}
	
	@And("I am still in cart page")
	public void cart_page_is_still_open() {
		Assert.assertTrue(expected_opened_webpage("cart"));
	}
	
	@Then("I proceed to checkout")
	public void checkout_page_is_open() {
		Assert.assertTrue(expected_opened_webpage("checkout"));
	}
}
