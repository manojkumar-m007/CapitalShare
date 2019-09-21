package com.capitalshare.testScripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.capitalshare.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseTest
{
	     @Test
      	 public void loginTest() throws IOException
      	 {
 
      		 LoginPage lp=new LoginPage(driver);
      		 lp.clickOnPortfolioLogin();
      		 logger.info("clicked on portfolio button");
      		 lp.setUserID(userID);
      		 logger.info("entered the username");
      		 lp.setUserPassword(userPassword);
      		 logger.info("entered the password");
      		 lp.clickOnLoginButton();
      		 logger.info("clicked on login button");
      		 if(driver.getTitle().equals("Live market news, Stock prices, Portfolio tracker | Capitalmarket"))
      		 {
      			 Assert.assertTrue(true);
      			 logger.info("Title is matched");
      		 }
      		 else
      		 {
      			 captureScreen(driver, "loginTest");
      			 Assert.assertFalse(false);
      			 logger.info("Title is Not matched");
      		 }
      	 }
}
	
