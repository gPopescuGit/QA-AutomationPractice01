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
	
	@FindBy(id="cancel")
	WebElement cancelBtn;
	
	public boolean totalValueWithoutTaxMatchesSumOfProductPrices() {
		double sum = 0;
		for (WebElement ele : cartItemsPrices) {
			double itemValue = Double.valueOf(ele.getText().split("\\$")[1]);
			sum+=itemValue;
		}
		double totalValue = Double.valueOf(totalWithoutTax.getText().split("\\$")[1]);		
		System.out.println(sum);
		System.out.println(totalValue);
		return sum==totalValue;
	}
	
	public ConfirmationPage finishOverview() {
		finishBtn.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}
	
	@FindBy(css = ".inventory_item_name")
	List<WebElement> productsOnOverviewPage;
	
	public int productsVisibleInOverviewPage() {
		return productsOnOverviewPage.size();
	}

	public void iClickOnButtonFromOverviewPage(String button) {
		switch (button) {
		case "Finish": {
			finishBtn.click();
			break;
		}
		case "Cancel": {
			cancelBtn.click();
			break;
		}

		default:
			throw new IllegalArgumentException("Unexpected value: " + button);
		}
		
	}
}
