package pageObjectsPackage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

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
	
	@FindBy(css = ".inventory_item_name ")
	List<WebElement> listProductNameElements;
	
	@FindBy(css = ".inventory_item_price")
	List <WebElement> listProductPriceElements;

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
			productAdded();
		} else {
			System.out.println("Product " + productName + " is already in cart");
		}

	}

	public void removeProductFromCart(String productName) {
		WebElement productScope = getProductByName(productName);
		if (productScope.findElement(addOrRemoveProduct).getText().equals("Remove")) {
			productScope.findElement(addOrRemoveProduct).click();
			productRemoved();
		} else {
			System.out.println("Product " + productName + " is NOT in cart");
		}

	}


	private int productsInCart = 0;

	public int cartIconQuantityEnabled() {
		return driver.findElements(By.cssSelector(".shopping_cart_badge")).size();
	}

	private void productAdded() {
		if (cartIconQuantityEnabled() > 0) {
			// there is at least one product in cart
			productsInCart++;
		} else {
			productsInCart = 1;
		}
	}

	private void productRemoved() {
		if (cartIconQuantityEnabled() > 0) {
			// there is at least one product in cart
			productsInCart--;
		} else {
			productsInCart = 0;
		}
	}

	public String howManyProductsInCart() {
		return String.valueOf(productsInCart);
	}

	public boolean checkExistingPageLinks() throws Exception {
		return checkBrokenLinks();
	}

	@FindBy(css = ".product_sort_container")
	WebElement selectFilterElement;

	public void selectFilter(String filterName) {
		Select dropdown = new Select(selectFilterElement);
		dropdown.selectByVisibleText(filterName);
	}

	public String getActiveFilter() {
		Select dropdown = new Select(selectFilterElement);
		return dropdown.getFirstSelectedOption().getText();
	}

	public boolean productsOrderedAccordingToActiveFilter() {
		List <String> productNames = new ArrayList<String>();
		for (WebElement e : listProductNameElements) {
			productNames.add(e.getText());
		}
		List <String> productPrices = new ArrayList<String>();
		for (WebElement e : listProductPriceElements) {
			productPrices.add(e.getText());
		}
		
		switch (getActiveFilter()) {
		case "Name (A to Z)": {
			List <String> sortedElements = productNames;
			Collections.sort(sortedElements);
			return productNames.equals(sortedElements);
		}
		case "Name (Z to A)": {
			List <String> sortedElements = productNames;
			Collections.sort(sortedElements, Collections.reverseOrder());
			return productNames.equals(sortedElements);
		}
		case "Price (low to high)": {
			List <String> sortedElements = productPrices;
			sortedElements.stream()
					.map(s -> s.replaceAll("[^\\d.]", ""))  // Remove non-numeric characters (like $)
                    .map(Double::parseDouble)  // Convert Strings to Integers
                    .sorted()                // Sort the Integers
                    .map(String::valueOf)    // Convert Integers back to Strings
                    .collect(Collectors.toList());  // Collect the result into a new list
			return productPrices.equals(sortedElements);
		}
		case "Price (high to low)": {
			List <String> sortedElements = productPrices;
			sortedElements.stream()
					.map(s -> s.replaceAll("[^\\d.]", ""))  // Remove non-numeric characters (like $)
                    .map(Double::parseDouble)  // Convert Strings to Integers
                    .sorted(Comparator.reverseOrder())                // Sort the Integers
                    .map(String::valueOf)    // Convert Integers back to Strings
                    .collect(Collectors.toList());  // Collect the result into a new list
			return productPrices.equals(sortedElements);
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + getActiveFilter());
		}

	}
	
	@FindBy(xpath = "//ul[@class='social']//li//a")
	List<WebElement>socialLinks;

	public void softCheckHyperlink(WebElement e) throws Exception {
			SoftAssert a = new SoftAssert();
			String url = e.getDomAttribute("href");
			if (url != null && !url.isEmpty() && (url.startsWith("http://") || url.startsWith("https://"))) {
				URI uri = new URI(url);
			    HttpURLConnection con = (HttpURLConnection) uri.toURL().openConnection();

			    con.setRequestMethod("HEAD");
			    int responseCode = con.getResponseCode();
			    System.out.println("URL: " + url + " | Status Code: " + responseCode);
				a.assertTrue(responseCode<400, "Link with Text: "+e.getText()+" with code "+responseCode);
			}

	}

	public void pressOnHyperlink() {
		for (WebElement e : socialLinks) {
			e.click();
		}
	}

	public void softCheckSocialHyperlinks() throws Exception {
		for (WebElement e : socialLinks) {
			softCheckHyperlink(e);
		}
	}
	
	

}
