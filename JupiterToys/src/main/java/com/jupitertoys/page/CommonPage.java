package com.jupitertoys.page;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.jupitertoys.base.BasePage;

public class CommonPage extends BasePage {

	By homePageButton = By.id("nav-home");
	By shopPageButton = By.id("nav-shop");
	By contactPageButton = By.id("nav-contact");
	By cartPage = By.xpath("//li[@id='nav-cart']/a");

	public HomePage navigatetoHomePage() {

		try {
			waitForElementPresent(homePageButton);
			driver.findElement(homePageButton).click();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return new HomePage();
	}
	
	public ShoppingPage navigatetoShoppingPage() {

		try {
			waitForElementPresent(shopPageButton);
			driver.findElement(shopPageButton).click();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return new ShoppingPage();
	}
	
	public CartPage navigatetoCartPage() {

		try {
			waitForElementPresent(cartPage);
			driver.findElement(cartPage).click();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return new CartPage();
	}

	public HomePage navigatetoContactPage() {

		try {
			waitForElementPresent(contactPageButton);
			driver.findElement(contactPageButton).click();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return new HomePage();
	}
	
	public int numberOfElement(By element) {

		int numOfElements = 0;

		try {
			numOfElements = driver.findElements(element).size();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return numOfElements;
	}
	
	@BeforeMethod
	public void setup() {

		initialization(prop.getProperty("browser"), prop.getProperty("url"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}


