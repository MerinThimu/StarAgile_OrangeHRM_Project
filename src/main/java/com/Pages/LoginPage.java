package com.Pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ActionDriver.Action;
import com.Base.BaseClass;

public class LoginPage extends BaseClass
{
	@FindBy (name ="username")
	WebElement UN;
	@FindBy (name = "password")
	WebElement PW;
	@FindBy (xpath = "//button[@type='submit']")
	WebElement submitBtn;
	@FindBy (xpath = "(//img[@alt='orangehrm-logo'])[2]")
	WebElement OrangeHRMLogo;
	
	public  LoginPage()
	{
		PageFactory.initElements(getDriver(), this);
	}
	public HomePage enterCredentials(String username,String password)
	{
		Action.explicitWait(getDriver(), UN, 10);
		Action.type(UN, username);
		Action.explicitWait(getDriver(), PW, 10);
		Action.type(PW, password);
		Action.explicitWait(getDriver(), submitBtn, 10);
		Action.click(getDriver(), submitBtn);
		System.out.println("clicked on submit button");
		return new HomePage();
	}	
	public boolean validateLogo()
	{
		Action.explicitWait(getDriver(), OrangeHRMLogo, 20);
		return Action.isDisplayed(getDriver(), OrangeHRMLogo);
	}
	public String getOrangeHRMTitle()
	{
		String orangeHrmTitle = getDriver().getTitle();
		return orangeHrmTitle;
	}
	

}
