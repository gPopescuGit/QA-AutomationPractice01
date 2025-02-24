package pageObjectsPackage;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponentPackage.AbstractComponent;

public class OverviewPage extends AbstractComponent{

	WebDriver driver;
	
	public OverviewPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='cart_item_label']//div[@class='inventory_item_price']")
	List<WebElement> cartItemsPrices;
	
	@FindBy(css=".summary_subtotal_label")
	WebElement totalWithoutTax;
	
	@FindBy(id="finish")
	WebElement finishBtn;
	
	public boolean checkTotalValueWithoutTax() {
		int sum = 0;
		for (WebElement ele : cartItemsPrices) {
			double itemValue = Double.valueOf(ele.getText().split("\\$")[1]);
			sum+=itemValue;
		}
		double totalValue = Double.valueOf(totalWithoutTax.getText().split("\\$")[1]);		
		return sum==totalValue;
	}
	
	public ConfirmationPage finishOverview() {
		finishBtn.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}
}
