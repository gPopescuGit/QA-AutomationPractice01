package pageObjectsPackage;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
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
	
	public void continueShopping() {
		continueShoppingBtn.click();
	}
	
	@FindBy(css = ".inventory_item_name")
	List<WebElement>listOfItems;
	
	public List<String> itemsInCart(){
		List<String> itemsInCart = new ArrayList<String>();
		for(WebElement e : listOfItems) {
			itemsInCart.add(e.getText());
		}
		return itemsInCart;
	}
	
	public void removeProductFromCart(String productName) {
		for(int i=0;i<listOfItems.size();i++) {
			if(listOfItems.get(i).getText().equals(productName)) {
				driver.findElements(By.cssSelector(".cart_button")).get(i).click();
			}
		}
	}

	public boolean productIsNotInCart(String productName) {
		boolean productNotInCart = true;
		for (WebElement e : listOfItems) {
			if(e.getText().equals(productName)) {
				productNotInCart = false;
			}
		}
		return productNotInCart;
		
	}

	public boolean cartPageOpen() {
		return driver.findElement(By.cssSelector("title")).getText().equals("Your Cart");
	}
	
	
}
