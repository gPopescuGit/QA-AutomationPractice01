package abstractComponentPackage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import pageObjectsPackage.CartPage;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".shopping_cart_link")
	WebElement cartHeader;

	@FindBy(id = "react-burger-menu-btn")
	WebElement menuHeader;

	public CartPage goToCartPage() {
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

	@FindBy(css=".title")
	WebElement getTitle;
	public String getPageTitle() {
		return getTitle.getText();
	}
	
	@FindBy(css = "ul[class='social'] li a")
	List<WebElement> footerLinks;
	
	//TODO:
	public boolean checkBrokenLinks() throws URISyntaxException, InterruptedException, IOException {
		for (WebElement link : footerLinks) {
			SoftAssert a = new SoftAssert();
			String url = link.getDomAttribute("href");
			//
			if (url != null && !url.isEmpty() && (url.startsWith("http://") || url.startsWith("https://"))) {
				URI uri = new URI(url);
				HttpURLConnection con = (HttpURLConnection) uri.toURL().openConnection();
				//twitter returns 403 - forbidden which requires login, its not broken
				con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");

				con.setRequestMethod("HEAD");
				int responseCode = con.getResponseCode();
				System.out.println("URL: " + url + " | Status Code: " + responseCode);
//					Assert.assertTrue(false); //hard assertion - stop test after fail
				a.assertTrue(responseCode < 400, "Link with Text: " + link.getText() + " with code " + responseCode);
			}
			Thread.sleep(500);

		}
		return false;
	}
}
