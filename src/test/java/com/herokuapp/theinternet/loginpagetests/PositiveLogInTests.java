package com.herokuapp.theinternet.loginpagetests;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.pages.SecureAreaPage;
import com.herokuapp.theinternet.pages.WelcomePage;

public class PositiveLogInTests extends TestUtilities {

		@Test
		public void logInTest() {
			// open main page
			WelcomePage welcomePage = new WelcomePage(driver, log);
			welcomePage.openPage();
			takeScreenshot("Welcome Page opened");

			// Click on Form Authentication link
			LoginPage loginPage = welcomePage.clickFormAuthenticationLink();
			takeScreenshot("Login Page opened");

			// Add new cookie
			Cookie ck = new Cookie("username", "tomsmith", "the-internet.herokuapp.com", "/", null);
			loginPage.setCookie(ck);

			// execute login
			SecureAreaPage secureAreaPage = loginPage.logIn("tomsmith", "SuperSecretPassword!");
			takeScreenshot("Secure Page opened");

			// Get cookies
			String username = secureAreaPage.getCookie("username");
			log.info("Username cookie: " + username);
			String session = secureAreaPage.getCookie("rack.session");
			log.info("Session cookie: " + session);

			// VERYFICATIONS
			// new url
			Assert.assertEquals(secureAreaPage.getCurrentUrl(), secureAreaPage.getPageUrl());

			// log out button is visible
			Assert.assertTrue(secureAreaPage.isLogOutButtonVisible(), "LogOut Button is not visible.");

			// Successful log in message
			String expectedSuccessMessage = "You logged into a secure area!";
			String actualSuccessMessage = driver.findElement(By.id("flash")).getText();
			Assert.assertTrue(actualSuccessMessage.contains(expectedSuccessMessage),
					"actualSuccessMessage does not contain expectedSuccessMessage\nexpectedSuccessMessage: "
							+ expectedSuccessMessage + "\nactualSuccessMessage: " + actualSuccessMessage);

		}
	}
