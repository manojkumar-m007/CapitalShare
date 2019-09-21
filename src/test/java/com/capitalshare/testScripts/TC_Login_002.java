package com.capitalshare.testScripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.capitalshare.pageObjects.LoginPage;
import com.capitalshare.utilites.XLUtils;

public class TC_Login_002 extends BaseTest 
{
	  @Test(dataProvider="LoginData")
      public void loginDDT(String user,String pwd) throws InterruptedException, IOException
      {
    	  LoginPage l=new LoginPage(driver);
    	  l.clickOnPortfolioLogin();
    	  logger.info("Clicked on Portfolio button");
    	  l.setUserID(user);
    	  logger.info("Entered the User ID");
    	  l.setUserPassword(pwd);
    	  logger.info("Entered the User Password");
    	  l.clickOnLoginButton();
    	  logger.info("Clicked on Login Button");
    	  Thread.sleep(3000);
    	  if(isAlertPresent()==true)
    	  {
    		  driver.switchTo().alert().accept();
    		  driver.switchTo().defaultContent();
    		  captureScreen(driver, "loginDDT");
    		  Assert.assertTrue(false);
    		  logger.warn("Login Failed");
    	  }
    	  else
    	  {
    		  Assert.assertTrue(true);
    		  logger.info("Login Passed");
    		  Thread.sleep(3000);
    		  l.clickOnLogoutButton();
    	  }
    	  
      }
      @DataProvider(name="LoginData")
      public String[][] getData() throws IOException
      {
    	  String path=System.getProperty("user.dir")+"/src/test/java/com/capitalshare/testData/LoginData.xlsx";
    	  int rownum=XLUtils.getRowCount(path, "Sheet1");
    	  int cellnum=XLUtils.getCellCount(path, "Sheet1", 1);
    	  String logindata[][]=new String[rownum][cellnum];
    	  for(int i=1;i<=rownum;i++)
    	  {
    		  for(int j=0;j<cellnum;j++)
    		  {
    			  logindata[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);
    		  }
    	  }
    	  return logindata;
      }
}
