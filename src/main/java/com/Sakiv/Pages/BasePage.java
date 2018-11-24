package com.Sakiv.Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Sakiv.Listners.ExtentListeners;
import com.Sakiv.Utilities.DriverManager;

public abstract class BasePage<T> {
	protected WebDriver driver;
	public static Logger log = Logger.getLogger(BasePage.class);

	public BasePage() {
		this.driver = DriverManager.getDriver();

	}

	public T openPage(Class<T> clazz) {
		T page = null;
		driver = DriverManager.getDriver();
		log.info("Driver Initialised");
		AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
		page = PageFactory.initElements(driver, clazz);
		log.info("Page Factory Initialised");
		PageFactory.initElements(ajax, page);
		ExpectedCondition pageLoadCondition = ((BasePage) page).getPageLoadCondition();
		log.info("Page Loaded");
		waitForPageToLoad(pageLoadCondition);

		return page;

	}

	private void waitForPageToLoad(ExpectedCondition pageLoadCondition) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(pageLoadCondition);
	}

	protected abstract ExpectedCondition getPageLoadCondition();

	public void enterValue(WebElement element, String value) {
		element.sendKeys(value);
		ExtentListeners.testReport.get().info("Entered " + value + " in " + element);

	}

	public void click(WebElement element) {
		element.click();
		ExtentListeners.testReport.get().info("Clicked on " + element);

	}
}
