package abstractComponentPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageObjectsPackage.CartPage;

public class AbstractComponent {

	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	
	@FindBy(css=".shopping_cart_link")
	WebElement cartHeader;
	
	@FindBy(id="react-burger-menu-btn")
	WebElement menuHeader;
	
	public CartPage goToCartPage() {
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	

}
