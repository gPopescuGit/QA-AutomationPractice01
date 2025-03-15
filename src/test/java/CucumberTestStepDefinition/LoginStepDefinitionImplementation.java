package CucumberTestStepDefinition;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjectsPackage.ConfirmationPage;
import pageObjectsPackage.InventoryPage;
import pageObjectsPackage.LandingPage;
import testComponents.BaseTest;

public class LoginStepDefinitionImplementation extends BaseTest {

	public LandingPage landingPage;
	public InventoryPage inventoryPage;
    
	@Given("I landed on ECommercePage") 
    public void i_landed_on_ECommercePage() throws IOException {
        landingPage = launchApplication();
    }

    @When("^I log in with username (.+) and password (.+)$")
    public void i_log_in_with_username_and_password(String username, String password) {
    	if(username.equals("<blank>")) {
    		inventoryPage = landingPage.loginApplication("", password);
    	}else if(password.equals("<blank>")) {
            inventoryPage = landingPage.loginApplication(username, "");
    	}else {
            inventoryPage = landingPage.loginApplication(username, password);
    	}
    }
	
	@Then("I logged in and landed on Inventory Page")
	public void i_logged_in_and_landed_on_inventory_page() {
		Assert.assertTrue(expected_opened_webpage("inventory"));
	}
	

	@Then ("Error message {string} is displayed on Login page")
	public void error_message_is_displayed_on_login_page(String errorMsg) {
		Assert.assertEquals(landingPage.getErrorMessage(), errorMsg);
	}
	
	@And ("Login Failed")
	public void login_Failed() {
		//login page is the expected page on failed log in attempt
		expected_opened_webpage("login");
	}
	

}
