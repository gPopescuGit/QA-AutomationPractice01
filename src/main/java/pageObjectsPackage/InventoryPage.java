package pageObjectsPackage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponentPackage.AbstractComponent;

public class InventoryPage extends AbstractComponent {

	WebDriver driver;

	public InventoryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".inventory_item")
	List<WebElement> products;

	By addOrRemoveProduct = By.cssSelector(".btn_inventory");

	public WebElement getProductByName(String productName) {
		WebElement product = products.stream()
				.filter(p -> p.findElement(By.cssSelector(".inventory_item_name")).getText().equals(productName))
				.findFirst().orElse(null);

		return product;
	}

	public void addProductToCart(String productName) {
		WebElement productScope = getProductByName(productName);
		if (productScope.findElement(addOrRemoveProduct).getText().equals("Add to cart")) {
			productScope.findElement(addOrRemoveProduct).click();
		}else {
			System.out.println("Product "+productName+" is already in cart");
		}

	}
}
