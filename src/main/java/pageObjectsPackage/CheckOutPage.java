package pageObjectsPackage;

import org.checkerframework.checker.units.qual.s;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponentPackage.AbstractComponent;

public class CheckOutPage extends AbstractComponent{
	WebDriver driver;



	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(id="first-name")
	WebElement firstNameTxt;

	@FindBy(id="last-name")
	WebElement lastNameTxt;
	
	@FindBy(id="postal-code")
	WebElement postalCodeTxt;
	
	@FindBy(id="continue")
	WebElement checkoutPageContinueButton;
	
	@FindBy(id="cancel")
	WebElement checkoutPageCancelButton;
	
	public OverviewPage completeCheckout(String firstName, String lastName, String postalCode) {
		firstNameTxt.sendKeys(firstName);
		lastNameTxt.sendKeys(lastName);
		postalCodeTxt.sendKeys(postalCode);
		checkoutPageContinueButton.click();
		OverviewPage overviewPage = new OverviewPage(driver);
		return overviewPage;
	}

	public boolean checkoutPageOpen() {
		return driver.findElement(By.cssSelector("title")).getText().equals("Checkout: Your Information");
		
	}
	
	public void fillFirstName(String string) {
		firstNameTxt.sendKeys(string);
	}
	
	public void fillLastName(String string) {
		lastNameTxt.sendKeys(string);
	}
	
	public void fillPostalCode(String string) {
		postalCodeTxt.sendKeys(string);
	}
	
	public void clickOnContinue() {
		checkoutPageContinueButton.click();
	}
	
	public void iClickOnButtonFromCheckoutPage(String button) {
		switch (button) {
		case "Continue": {
			checkoutPageContinueButton.click();
			break;
		}
		case "Cancel": {
			checkoutPageCancelButton.click();
			break;
		}

		default:
			throw new IllegalArgumentException("Unexpected value: " + button);
		}
	}

	@FindBy(css = ".error-message-container")
	WebElement errorMessageContainer;
	
	public String errorMessageDisplayed() {
		return errorMessageContainer.getText();
	}
}
