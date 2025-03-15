package pageObjectsPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponentPackage.AbstractComponent;

//Login page
public class LandingPage extends AbstractComponent {

	public WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "user-name")
	WebElement userName;
	
	@FindBy(id = "password")
	WebElement userPassword;
	
	@FindBy(id = "login-button")
	WebElement loginBtn;
	
	public InventoryPage loginApplication(String user, String pass) {
		userName.sendKeys(user);
		userPassword.sendKeys(pass);
		loginBtn.click();
		InventoryPage inventoryPage = new InventoryPage(driver);
		return inventoryPage;
	}
	
	public void goTo() {
		driver.get("https://www.saucedemo.com/");// waits to fully load page
	}
	
	@FindBy(xpath = "//h3[@data-test='error']")
	WebElement errorMessageContainer;
	
	public String getErrorMessage() {
		return errorMessageContainer.getText();
	}
}
