package com.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ActionDriver.Action;
import com.Utility.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static Properties prop;
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

	@BeforeSuite
	public void loadConfig() {
		ExtentManager.setExtent();
		DOMConfigurator.configure("log4j.xml");
		try {
			prop = new Properties();
			System.out.println("super constructor invoked");
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/configuration/config.properties");
			prop.load(ip);

			System.out.println("Configuration file loaded successfully.");

		} catch (FileNotFoundException e) {
			System.out.println("Configuration file not found!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error loading configuration file!");
			e.printStackTrace();
		}

	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void launchApp(String browserName) {

		if (browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
		} else if (browserName.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver.set(new EdgeDriver());
		}

		getDriver().manage().window().maximize();
		Action.implicitWait(getDriver(), 10);
		Action.pageLoadTimeOut(getDriver(), 30);
		getDriver().get(prop.getProperty("url"));
	}

	@AfterSuite
	public void afterSuite() {

		ExtentManager.endReport();

	}

}