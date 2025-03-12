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
	
	@Then("Inventory page is opened")
	public void inventory_page_is_opened() {
		Assert.assertEquals(driver.findElement(By.cssSelector(".title")).getText(), "Products");	
		driver.close();
	}
	

	@Then ("Error message {string} is displayed")
	public void error_message_is_displayed(String string) {
		Assert.assertEquals(driver.findElement(By.xpath("//h3[@data-test='error']")).getText(), string);
	}
	
	@And ("Login Failed")
	public void login_Failed() {
		Assert.assertEquals(driver.findElement(By.cssSelector(".login_logo")).getText(), "Swag Labs");	
		driver.close();
	}
	

}
