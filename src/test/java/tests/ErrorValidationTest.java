package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjectsPackage.CartPage;
import pageObjectsPackage.InventoryPage;
import testComponents.BaseTest;

public class ErrorValidationTest extends BaseTest {

	By error = By.xpath("//div//h3[@data-test='error']"); 
	
	@Test(groups="ErrorHandling")
	@Parameters({"fooEmail","fooPass"})
	public void loginWrongCredentials(String email, String pass) {
		landingPage.loginApplication(email, pass);
		String errorMsg = driver.findElement(error).getText();
		Assert.assertEquals(errorMsg, "Epic sadface: Username and password do not match any user in this service");
	}
	
	@Test(groups="ErrorHandling")
	@Parameters({"lockedEmail", "pass"})
	public void loginLockedUser(String email, String pass) {
		landingPage.loginApplication(email, pass);
		String errorMsg = driver.findElement(error).getText();
		Assert.assertEquals(errorMsg, "Epic sadface: Sorry, this user has been locked out.");

	}

	@Test(dataProvider="getData", groups="ErrorHandling")
	public void fillWrongCheckoutPageInfo(HashMap<String, String> input) {
		InventoryPage inventoryPageObject = landingPage.loginApplication(input.get("email"), input.get("pass"));
		CartPage cartPage = inventoryPageObject.goToCartPage();
		cartPage.goToCheckout().completeCheckout(//
				input.get("fName"), //
				input.get("lName"), //
				input.get("postalCode"));
		String errorMsg = driver.findElement(error).getText();
		Assert.assertTrue(errorMsg.contains("Error"));
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJSonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//resources//WrongCheckoutInfo.json");
		Object[][] o = new Object[data.size()][1];
		for(int i=0;i<data.size();i++) {
			o[i][0] = data.get(i);
		}
		return o;
	}
	
	@Test(groups="ErrorHandling")
	public void takeScreenShotOfFailedTest() {
		Assert.fail("Intentionally failing this test to showcase screenshot feature.");
	}
}
