package com.capitalshare.utilites;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

// This is the Listener class used to generate extent report
public class Reporting extends TestListenerAdapter 
{
     ExtentHtmlReporter htmlReporter;
     ExtentReports extent;
     ExtentTest logger;
     
    public void onStart(ITestContext testContext)
    {
    	String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	String repName="Test-Report-"+timeStamp+".html";
    	htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+repName);
    	htmlReporter.loadConfig(System.getProperty("user.dir")+"/extent-config.xml");
    	
    	extent=new ExtentReports();
    	extent.attachReporter(htmlReporter);
    	extent.setSystemInfo("Host name", "localhost");
    	extent.setSystemInfo("Environment", "QA");
    	extent.setSystemInfo("user", "MANOJKUMAR M");
    	
    	htmlReporter.config().setDocumentTitle("CapitalShare Test Project");//title of the report
    	htmlReporter.config().setReportName("Functional Test Automation Report");//name of the report
    	htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);//location of the chart
    	htmlReporter.config().setTheme(Theme.DARK);
    }
    
    public void onTestSuccess(ITestResult tr)
    {
    	logger=extent.createTest(tr.getName());//create new entry in the report
    	logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));//send the passed information
    }
    
    public void onTestFailure(ITestResult tr)
    {
    	logger=extent.createTest(tr.getName());//create new entry in the report
    	logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));//send the fail information
    	
    	String screenshotpath=System.getProperty("user.dir")+"/ScreenShots/"+tr.getName()+".png";
    	File f=new File(screenshotpath);
    	
    	if(f.exists())
    	{
    		try
    		{
    			logger.fail("Screenshot is below:"+logger.addScreenCaptureFromPath(screenshotpath));
    		}
    		catch(IOException e)
    		{
    			e.printStackTrace();
    		}
    	}
    }
    
    public void onTestSkipped(ITestResult tr)
    {
    	logger=extent.createTest(tr.getName());//create new entry in the report
    	logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
    }
    
    public void onFinish(ITestContext testContext)
    {
    	extent.flush();
    }
    
}
