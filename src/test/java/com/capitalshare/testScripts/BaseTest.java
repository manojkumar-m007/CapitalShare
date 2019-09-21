package com.capitalshare.testScripts;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.capitalshare.utilites.ReadConfig;

public class BaseTest 
{
	   ReadConfig rcf=new ReadConfig();
       public String baseURL=rcf.getUrl();
       public String userID=rcf.getUserID();
       public String userPassword=rcf.getUserPassword();
       //public String expectedTitle="Live market news, Stock prices, Portfolio tracker | Capitalmarket";
       public WebDriver driver;
       public Logger logger;
       
       @Parameters("browsers")
       @BeforeClass
       public void setUp(String browser)
       {
    	   logger =Logger.getLogger("CapitalShare");
    	   PropertyConfigurator.configure("log4j.properties");
    	   if(browser.equalsIgnoreCase("chrome"))
    	   {
    		   driver=new ChromeDriver();
    	   }
    	   else if(browser.equalsIgnoreCase("firefox"))
    	   {
    		   driver=new FirefoxDriver();
    	   }
    	   else if(browser.equalsIgnoreCase("ie"))
    	   {
    		   driver=new InternetExplorerDriver();
    	   }
    	   driver.get(baseURL);
    	   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       }
       @AfterClass
       public void tearDown() throws InterruptedException
       {
    	   Thread.sleep(3000);
    	   driver.close();
       }
       
       public void captureScreen(WebDriver driver,String testname) throws IOException
       {
    	   TakesScreenshot ts=(TakesScreenshot)driver;
    	   File source=ts.getScreenshotAs(OutputType.FILE);
    	   File target=new File(System.getProperty("user.dir")+"/ScreenShots/"+testname+".png");
    	   FileHandler.copy(source, target);
    	   Reporter.log("Failed", true);
       }
       
       public boolean isAlertPresent()
       {
    	   try
    	   {
    		   driver.switchTo().alert();
    		   return true;
    	   }
    	   catch(NoAlertPresentException e)
    	   {
    		   return false;
    	   }
       }
}
