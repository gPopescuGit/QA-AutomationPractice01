package tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InitialTest {


	@Test
	public void testBeforeRefactor() throws InterruptedException, MalformedURLException, IOException {
		//init driver
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");//
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		//login page; login with standart user
		driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
		driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
		driver.findElement(By.cssSelector("#login-button")).click();
		//inventory page; add products in 2 ways
		//1.by visible button on inventory page
		driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack")).click();
		//2.by entering page, adding to cart then returning to inventory page
		//TODO: add code here
		//cart page; check products in cart
		driver.findElement(By.xpath("//a[@data-test='shopping-cart-link']")).click();
		//TODO: add code here
		//checkout page; insert data
		driver.findElement(By.id("checkout")).click();
		driver.findElement(By.id("first-name")).sendKeys("first name");
		driver.findElement(By.id("last-name")).sendKeys("last name");
		driver.findElement(By.id("postal-code")).sendKeys("1234");
		driver.findElement(By.cssSelector("#continue")).click();
		driver.findElement(By.id("finish")).click();
		//confirmation page; check confirmation message
		Assert.assertEquals(driver.findElement(By.xpath("//h2[@data-test='complete-header']")).getText(),
				"Thank you for your order!");

	}
}
