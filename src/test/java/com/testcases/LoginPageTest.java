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
import com.Utility.Log;

public class LoginPageTest extends BaseClass {
	LoginPage loginpage;
	HomePage homepage;

	@Parameters("browser")
	@BeforeMethod
	public void setup(String browser) {
		launchApp(browser);
		loginpage = new LoginPage();
	}

	@AfterMethod
	public void teardown() {
		getDriver().quit();
	}

	@Test
	public void verifyLogo() {
		Log.startTestCase("verifyLogo");
		loginpage = new LoginPage();
		boolean result = loginpage.validateLogo();
		Assert.assertTrue(result, "Logo not displayed");
		Log.info("Logo is Displayed");
	}

	@Test
	public void verifyTitle() {
		Log.startTestCase("verifyTitle");
		String actTitle = loginpage.getOrangeHRMTitle();
		Assert.assertEquals(actTitle, "OrangeHRM");
		Log.info("Title is Displayed");
		Log.endTestCase("verifyTitle");
	}

	@Test(dataProvider = "credentials", dataProviderClass = DataProviders.class)
	public void loginTest(String uname,String pswd) {
		Log.startTestCase("loginTest");
		homepage = new HomePage();
		
		
		homepage = loginpage.enterCredentials(uname, pswd);
		Log.info("Login success!");
		
		String actUrl = homepage.getHomepageUrl();
		String expUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
		Assert.assertEquals(actUrl, expUrl);
		Log.info("HomePage url verified");
		Log.endTestCase("loginTest");
	}

}
