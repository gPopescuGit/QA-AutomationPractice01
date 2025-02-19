package pageObjectsPackage;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponentPackage.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="checkout")
	WebElement checkoutBtn;
	
	@FindBy(id="continue-shopping")
	WebElement continueShoppingBtn;
	
	@FindBy(css=".inventory_item_name")
	List<WebElement> cartProducts;
	
	public Boolean VerifyProductDisplay(String productName) {
		Boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage goToCheckout() {
		checkoutBtn.click();
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		return checkOutPage;
	}

	
	
	
	
}
