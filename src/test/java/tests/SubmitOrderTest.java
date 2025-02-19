package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjectsPackage.CartPage;
import pageObjectsPackage.InventoryPage;
import pageObjectsPackage.OverviewPage;
import testComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) {

		InventoryPage inventoryPageObject = landingPage.loginApplication(input.get("email"), input.get("pass"));
		inventoryPageObject.addProductToCart(input.get("product"));
		CartPage cartPage = inventoryPageObject.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertEquals(match, true);
		OverviewPage overviewPage = cartPage.goToCheckout().completeCheckout(//
				input.get("fName"), //
				input.get("lName"), //
				input.get("postalCode"));
		overviewPage.checkTotalValueWithoutTax();
		overviewPage.finishOverview().confirmOrder();

	}

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitMultipleOrders(HashMap<String, String> input) {

		InventoryPage inventoryPageObject = landingPage.loginApplication(input.get("email"), input.get("pass"));
		inventoryPageObject.addProductToCart("Sauce Labs Bolt T-Shirt");
		inventoryPageObject.addProductToCart("Sauce Labs Bolt T-Shirt");// existing product
		inventoryPageObject.addProductToCart("Sauce Labs Fleece Jacket");
		inventoryPageObject.addProductToCart("Sauce Labs Onesie");
		inventoryPageObject.addProductToCart("Sauce Labs Bike Light");

		CartPage cartPage = inventoryPageObject.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("Sauce Labs Bolt T-Shirt");
		// check "Sauce Labs Bolt T-Shirt" was not removed
		Assert.assertEquals(match, true);
		OverviewPage overviewPage = cartPage.goToCheckout().completeCheckout(//
				input.get("fName"), //
				input.get("lName"), //
				input.get("postalCode"));
		overviewPage.checkTotalValueWithoutTax();
		overviewPage.finishOverview().confirmOrder();

	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJSonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//resources//PurchaseOrder.json");

		//instead of
		//Object[][] o = new Object[][]{ { data.get(0) }, { data.get(1) }, { data.get(2) } };
		//use
		Object[][] o = new Object[data.size()][1];
		for(int i=0;i<data.size();i++) {
			o[i][0] = data.get(i);
		}
		return o;
	}
}
