package com.Sakiv.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;

import com.Sakiv.Listners.ExtentListeners;
import com.Sakiv.Utilities.DriverFactory;
import com.Sakiv.Utilities.DriverManager;

public class BaseTest {
	private static WebDriver driver;
	private Properties properties = new Properties();
	private FileInputStream fis;
	public static Logger log = Logger.getLogger(BaseTest.class);

	@BeforeSuite
	public void frameworkSetUp() {
		configureLogging();
		DriverFactory.setgridPath("http://localhost:4444/wd/hub");
		DriverFactory.setconfigPropertyPath(
				System.getProperty("user.dir") + ("//src//test//resources//Properties//config.properties"));
		DriverFactory.setgeckoExePath(
				System.getProperty("user.dir") + ("//src//test//resources//com//Sakiv//Grid//deckodriver.exe"));
		DriverFactory.setchromeExePath(
				System.getProperty("user.dir") + ("//src//test//resources//com//Sakiv//Grid//chromedriver.exe"));
		try {
			fis = new FileInputStream(DriverFactory.getconfigPropertyPath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			properties.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Config File Loaded");

	}

	public void configureLogging() {
		String log4jConfigFile = System.getProperty("user.dir")
				+ ("//src//test//resources//Properties//log4j.properties");
		PropertyConfigurator.configure(log4jConfigFile);
	}

	public void logInfo(String message) {
		ExtentListeners.testReport.get().info(message);

	}

	public static void initiateBrowser(String browser) {
		DesiredCapabilities cap = null;
		DriverFactory.setisRemote(true);

		if (DriverFactory.getisRemote()) {
			if (browser.equalsIgnoreCase("chrome")) {
				cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
				cap.setPlatform(Platform.ANY);
				log.info("Chrome Driver Initialised");

			} else if (browser.equalsIgnoreCase("firefox")) {
				cap = DesiredCapabilities.chrome();
				cap.setBrowserName("firefox");
				cap.setPlatform(Platform.ANY);
				log.info("FireFox Driver Initialised");

			}

			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
				log.info("Execution started on Grid");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		DriverManager.setDriver(driver);
		DriverManager.getDriver().manage().window().maximize();
		DriverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	public void quitBrowser() {
		DriverManager.getDriver().quit();
		log.info("Driver Instance terminated");
		logInfo("Test Completed");
	}

}
