package com.Pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ActionDriver.Action;
import com.Base.BaseClass;
import com.Utility.Log;

public class HomePage extends BaseClass {
	@FindBy(xpath = "//h6[text()='Dashboard']")
	WebElement dashBoardImg;

	public HomePage() {
		PageFactory.initElements(getDriver(), this);
	}

	public boolean validateText() {

		Action.explicitWait(getDriver(), dashBoardImg, 10);

		return Action.isDisplayed(getDriver(), dashBoardImg);

	}

	public String getHomepageUrl() {
		Log.startTestCase("getHomepageUrl");
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
		wait.until(ExpectedConditions.urlContains("/dashboard/index"));
		Log.info("Homepage url verified");
		String homeurl = getDriver().getCurrentUrl();
		return homeurl;
	}
}
