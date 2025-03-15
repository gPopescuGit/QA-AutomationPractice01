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
	
	@When("I click on selected product \"Remove\" button")
	public void i_click_on_product_remove_button() {
		cartPage.removeProductFromCart(addedProductInCart);
	}
	
	@Then("Product is removed from Cart")
	public void product_removed_from_cart() {
		Assert.assertTrue(cartPage.productIsNotInCart(addedProductInCart));
	}
	
	@When("I click on \"Checkout\" button")
	public void i_click_on_checkout_button() {
		cartPage.goToCheckout();
	}
	
	@When("I click on \"Continue Shopping\"")
	public void i_click_on_continue_shopping() {
		cartPage.continueShopping();
	}
	
	@Then("Inventory page is open")
	public void inventory_page_open() {
		inventoryPage.inventoryPageOpen();
	}
	
	@Then("I am notified that Cart is empty")
	public void i_am_notified_that_cart_is_empty(){
		//there is no notification implemented in webpage
		Assert.assertTrue(false, "Cart is empty, cannot proceed to checkout");
	}
	
	@And("Cart page is still open")
	public void cart_page_is_still_open() {
		Assert.assertTrue(cartPage.cartPageOpen());
	}
	
	@Then("Checkout page is opened")
	public void checkout_page_is_open() {
		Assert.assertTrue(checkOutPage.checkoutPageOpen());
	}
}
