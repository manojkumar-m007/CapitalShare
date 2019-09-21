package com.capitalshare.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
	public WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(xpath="//div[@id='logintxt']")
	WebElement btnpLogin;
	@FindBy(xpath="//input[@id='Header1_txtUserID']")
	WebElement txtUserID;
	@FindBy(xpath="//input[@id='Header1_txtUserPassword']")
	WebElement txtUserPassword;
	@FindBy(xpath="//input[@id='Header1_btnLogin']")
	WebElement btnLogin;
	@FindBy(xpath="//div[@class='col-sm-2 col-md-4 col-lg-2 menu-user nopads pull-right']")
	WebElement btnLogout;
	
	public void clickOnPortfolioLogin()
	{
		btnpLogin.click();
	}
	public void setUserID(String userid)
	{
		txtUserID.sendKeys(userid);
	}
	public void setUserPassword(String userpassword)
	{
		txtUserPassword.sendKeys(userpassword);
	}
	public void clickOnLoginButton()
	{
		btnLogin.click();
	}
	public void clickOnLogoutButton()
	{
		btnLogout.click();
	}
}
   