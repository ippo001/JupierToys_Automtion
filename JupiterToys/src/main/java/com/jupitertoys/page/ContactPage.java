package com.jupitertoys.page;

import org.openqa.selenium.By;

import com.github.javafaker.Faker;

public class ContactPage extends CommonPage {
	
	public static String fName = null;

	By submitButton = By.xpath("//a[text()='Submit']");
	By firstName = By.id("forename");
	By email = By.id("email");
	By message = By.id("message");
	By firstNameError = By.id("forename-err");
	By emailError = By.id("email-err");
	By messageError = By.id("message-err");
	By successMessage = By.xpath("//div[@ui-if='contactValidSubmit']/div[1]");

	Faker fake = new Faker();

	public ContactPage clickSubmitButton() {

		try {
			waitForElementPresent(submitButton);
			driver.findElement(submitButton).click();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	public ContactPage verifyErrorMessagesPresent() {

		clickSubmitButton();
		waitForElementPresent(firstNameError);
		waitForElementPresent(emailError);
		waitForElementPresent(messageError);

		return this;
	}

	public String getFirstNameErr() {

		return driver.findElement(firstNameError).getText().trim();
	}

	public String getEmailNameErr() {

		return driver.findElement(emailError).getText().trim();
	}

	public String getMessageNameErr() {

		return driver.findElement(messageError).getText().trim();
	}

	public ContactPage enterMandatoryDetails() {
		boolean flag = false;
		try {
			
			waitForElementPresent(firstName);
			fName = fake.name().firstName();
			driver.findElement(firstName).sendKeys(fName);
			driver.findElement(email).sendKeys(fake.internet().emailAddress());
			driver.findElement(message).sendKeys("Hi, This is a test message");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	public int numberOfFirstNameErrorElement() {

		return numberOfElement(firstNameError);

	}

	public int numberOfEmailErrorElement() {

		return numberOfElement(emailError);
	}

	public int numberOfMessageElement() {

		return numberOfElement(messageError);

	}

	public String getSuccessMessage() {

		waitForElementPresent(successMessage);
		return driver.findElement(successMessage).getText().trim();
	}
}
