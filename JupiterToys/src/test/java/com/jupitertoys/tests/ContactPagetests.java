package com.jupitertoys.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jupitertoys.page.CommonPage;
import com.jupitertoys.page.ContactPage;

public class ContactPagetests extends CommonPage {

	ContactPage contactPage = new ContactPage();

	@Test(priority = 1, description = "Verifying the error messages")
	public void verifyErrorMessages() {

		navigatetoContactPage();
		contactPage.verifyErrorMessagesPresent();

		String firstNameErr = contactPage.getFirstNameErr();
		Assert.assertEquals(firstNameErr, prop.getProperty("firstNameErrorMsg"));

		String emailErr = contactPage.getEmailNameErr();
		Assert.assertEquals(emailErr, prop.getProperty("emailErrorMsg"));

		String messageErr = contactPage.getMessageNameErr();
		Assert.assertEquals(messageErr, prop.getProperty("messageErrorMsg"));

		contactPage.enterMandatoryDetails();
		int firstNameErrorNumber = contactPage.numberOfFirstNameErrorElement();
		Assert.assertEquals(firstNameErrorNumber, 0);

		int emailErrorNumber = contactPage.numberOfEmailErrorElement();
		Assert.assertEquals(emailErrorNumber, 0);

		int messageErrorNumber = contactPage.numberOfMessageElement();
		Assert.assertEquals(messageErrorNumber, 0);
	}

	@Test(priority = 2, description = "Verify success message on filing form", invocationCount = 5)
	public void submitForm() throws InterruptedException {
		
		String title = driver.getTitle().trim();
		Assert.assertEquals(title, prop.getProperty("homePageTitle"));
		navigatetoContactPage();
		contactPage.enterMandatoryDetails();
		contactPage.clickSubmitButton();
		Thread.sleep(10000);
		String successMsg = contactPage.getSuccessMessage();
		Assert.assertEquals(successMsg, "Thanks " + ContactPage.fName + ", we appreciate your feedback.");
	}
}
