package pageObjectsPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponentPackage.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".complete-header")
	WebElement confirmationTxt;
	
	@FindBy(id = "back-to-products")
	WebElement backToProductsBtn;
	
	public boolean confirmOrder() {
		return confirmationTxt.getText().equals("Thank you for your order!");
	}
	
	public void getBackToProducts(String button) {
		switch (button) {
		case "Back to Home": {
			backToProductsBtn.click();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + button);
		}
	}
}
