package com.Sakiv.TestCases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.Sakiv.Base.BaseTest;
import com.Sakiv.Pages.LoginPage;
import com.Sakiv.Utilities.Constants;
import com.Sakiv.Utilities.DataProviders;
import com.Sakiv.Utilities.DataUtil;
import com.Sakiv.Utilities.ExcelReader;

public class LoginTestFireFox extends BaseTest {

	@Test(dataProviderClass = DataProviders.class, dataProvider = "RegressionDP")
	public void loginTest(Hashtable<String, String> data) {
		log.info("Execution started on " + data.get("browser"));
		logInfo("Execution started on "+ data.get("browser"));
		ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
		DataUtil.checkExecution("Regression", "LoginTest", data.get("Runmode"), excel);

		initiateBrowser(data.get("browser"));

		LoginPage login = new LoginPage().open("http://newtours.demoaut.com/");
		login.doLogin(data.get("userName"), data.get("password"));
		log.info("User Logged in");
		logInfo("User Logged Id");
		logInfo("Browser Terminated");
		quitBrowser();
		log.info("Browser Terminated");
	}

}