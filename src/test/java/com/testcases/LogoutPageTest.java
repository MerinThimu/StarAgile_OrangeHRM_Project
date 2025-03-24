package com.testcases;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Base.BaseClass;
import com.DataProvider.DataProviders;
import com.Pages.HomePage;
import com.Pages.LoginPage;
import com.Pages.LogoutPage;
import com.Utility.Log;

public class LogoutPageTest extends BaseClass
{
	LoginPage loginpage;
	HomePage homepage;
	LogoutPage logoutpage;

	@Parameters("browser")
	@BeforeMethod
	public void setup(String browser) {
		launchApp(browser);
		loginpage = new LoginPage();
	}

	@AfterMethod
	public void teardown() 
	{
		getDriver().quit();
	}
	@Test(dataProvider = "credentials", dataProviderClass = DataProviders.class)
	public void LogOutPage(String uname,String pswd)
	{
	Log.startTestCase("verifyText");
	homepage = new HomePage();
	
	homepage = loginpage.enterCredentials(uname, pswd);
	Log.info("Login Success");
	
	String actUrl = homepage.getHomepageUrl();
	String expUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
	Assert.assertEquals(actUrl, expUrl);
	Log.info("HomePage url verified");
	
	boolean result = homepage.validateText();
	Assert.assertTrue(result);
	Log.info("Text - Dashboard is verified");
	Log.endTestCase("verifyText");

	 logoutpage = new LogoutPage();
	 loginpage = logoutpage.logout();
	Log.info("Logout Success!");
	
	}

}
