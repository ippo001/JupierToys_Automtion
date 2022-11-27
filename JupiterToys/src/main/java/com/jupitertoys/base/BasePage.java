package com.jupitertoys.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	public static FileInputStream fis = null;
	public static Properties prop = null;
	String filePath = System.getProperty("user.dir") + "/src/test/resources/config.properties";

	public BasePage() {

		try {
			fis = new FileInputStream(filePath);
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static WebDriver driver = null;

	/**
	 * Initialisation of the Test
	 * 
	 * @param browser
	 * @param version
	 * @param platform
	 */
	public void initialization(String browser, String url) {

		if (browser.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		else {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}

	/**
	 * Get Title
	 * 
	 * @return
	 */
	public String getTitle() {

		return driver.getTitle().trim();
	}

	WebDriverWait wait = null;

	/**
	 * Wait for text to be present on screen
	 * 
	 * @param element
	 * @param text
	 * @return
	 */
	public boolean waitForTextPresentinWebElement(WebElement element, String text) {

		wait = new WebDriverWait(driver, 20);
		return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}

	/**
	 * Wait for element to appear
	 * 
	 * @param element
	 * @return
	 */
	public WebElement waitForElementPresent(By by) {

		wait = new WebDriverWait(driver, 20);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	/**
	 * Wait for element to disappear
	 * 
	 * @param element
	 * @return
	 */
	public boolean waitForElementInvisibility(WebElement element) {

		wait = new WebDriverWait(driver, 20);
		return wait.until(ExpectedConditions.invisibilityOf(element));
	}

	/**
	 * Wait For Element To be Non Stale
	 * 
	 * @param element
	 * @return
	 */
	public boolean waitForElementStalenessOfElement(WebElement element) {

		wait = new WebDriverWait(driver, 20);
		return wait.until(ExpectedConditions.stalenessOf(element));
	}

	/**
	 * Wait For Page To Load Completely
	 */
	public void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			wait = new WebDriverWait(driver, 30);
			wait.until(expectation);
		} catch (Throwable error) {
			System.out.println("Timeout waiting for Page Load Request to complete.");
		}
	}

	/**
	 * Verify element is enable
	 * 
	 * @param element
	 * @return
	 */
	public boolean elementEnable(WebElement element) {
		wait = new WebDriverWait(driver, 20);
		return element.isEnabled();
	}
}