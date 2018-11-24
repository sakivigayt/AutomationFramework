package com.Sakiv.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.Sakiv.Utilities.DriverManager;

public class LoginPage extends BasePage {
	@FindBy(xpath = "//input[@name='userName']")
	public WebElement uName;

	@FindBy(xpath = "//input[@name='password']")
	public WebElement password;

	@FindBy(xpath = "//input[@value='Login']")
	public WebElement signIn;

	WebDriver driver;

	public LoginPage open(String url) {
		DriverManager.getDriver().navigate().to(url);
		log.info("Navigated to " + url);
		return (LoginPage) openPage(LoginPage.class);
	}

	public void doLogin(String username, String passwd) {

		enterValue(uName, username);
		log.info("entered value "+ username + " in " + uName);
		enterValue(password, passwd);
		log.info("entered value "+ passwd + " in " + password);
		click(signIn);
		log.info("clicked on "+ signIn);

	}

	@Override
	protected ExpectedCondition getPageLoadCondition() {

		return ExpectedConditions.visibilityOf(uName);
	}

}
