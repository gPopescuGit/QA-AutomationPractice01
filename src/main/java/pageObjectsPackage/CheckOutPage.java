package pageObjectsPackage;

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
	
	/*
	 * //checkout page; insert data driver.findElement(By.id("checkout")).click();
	 * driver.findElement(By.id("first-name")).sendKeys("first name");
	 * driver.findElement(By.id("last-name")).sendKeys("last name");
	 * driver.findElement(By.id("postal-code")).sendKeys("1234");
	 * driver.findElement(By.cssSelector("#continue")).click();
	 */
	
	@FindBy(id="first-name")
	WebElement firstNameTxt;

	@FindBy(id="last-name")
	WebElement lastNameTxt;
	
	@FindBy(id="postal-code")
	WebElement postalCodeTxt;
	
	@FindBy(id="continue")
	WebElement goToOverviewPageBtn;
	
	public OverviewPage completeCheckout(String firstName, String lastName, String postalCode) {
		firstNameTxt.sendKeys(firstName);
		lastNameTxt.sendKeys(lastName);
		postalCodeTxt.sendKeys(postalCode);
		goToOverviewPageBtn.click();
		OverviewPage overviewPage = new OverviewPage(driver);
		return overviewPage;
	}
	
	
}
