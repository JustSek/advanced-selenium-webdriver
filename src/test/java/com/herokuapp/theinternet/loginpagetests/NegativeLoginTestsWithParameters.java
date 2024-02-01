package com.herokuapp.theinternet.loginpagetests;
import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeLoginTestsWithParameters extends TestUtilities {

	@Parameters({ "username", "password", "expectedMessage" })
	@Test(priority = 1)
	public void negativeLoginTestsWithParameters(String username, String password, String expectedErrorMessage) {
		// open main page
		WelcomePage welcomePageObject = new WelcomePage(driver, log);
		welcomePageObject.openPage();

		// Click on Form Authentication link
		LoginPage loginPage = welcomePageObject.clickFormAuthenticationLink();

		// execute negative login and wait for error message
		loginPage.negativeLogIn(username, password);
		loginPage.waitForErrorMessage();

		String message = loginPage.getErrorMessageText();

		// Verification
		Assert.assertTrue(message.contains(expectedErrorMessage), "Message doesn't contain expected text.");
	}

}
