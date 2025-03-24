package com.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.ActionDriver.Action;
import com.Base.BaseClass;


public class LogoutPage extends BaseClass
{
	@FindBy (xpath = "//img[@alt='profile picture']")
	WebElement profilePic;
	
	@FindBy (xpath = "//a[text()='Logout']")
	WebElement logoutBtn;
	
	public  LogoutPage()
	{
		PageFactory.initElements(getDriver(), this);
	}
	
	public LoginPage logout()
	{
		Action.explicitWait(getDriver(),profilePic , 20);
		Action.click(getDriver(), profilePic);
		
		Action.click(getDriver(), logoutBtn);
		return new LoginPage();
	}

}
