package com.qst.ohrm.test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qst.ohrm.page.OrangeDashboardPage;
import com.qst.ohrm.page.OrangeHRMLoginPage;
import com.qst.ohrm.utils.ConfigFileReader;
import com.qst.ohrm.utils.DriverUtils;
import com.qst.ohrm.utils.ExtentReportFactory;
import com.qst.ohrm.utils.Log;
import com.qst.ohrm.utils.OrangeHRMUtils;
import com.qst.ohrm.utils.Screenshots;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class LoginInRemote {

	private WebDriver driver = null;
	OrangeHRMLoginPage oLp= null;
	OrangeDashboardPage odp = null;
	
	static ConfigFileReader configFileReader;
	DriverUtils dUtils=null;
	ExtentReports reports;
	ExtentTest test;
 	
	@BeforeClass
 	public void setup(){
 		configFileReader= new ConfigFileReader();
 		Log.configureReport();
 		Log.startReport("setup");
		driver = DriverUtils.getRemoteFFDriver();
		oLp = new OrangeHRMLoginPage(driver,test);
		odp = new OrangeDashboardPage(driver,test);
 	}
 	
	@Test
	public void validateLoginTest(){
		Log.startReport("validateLoginTest");
		Log.info("--Started Executing Test - validateLoginTest");
		OrangeHRMUtils.launchRemoteApp(driver,configFileReader.getApplicationUrl());
		Log.pass("Launched application successfully");
		oLp.loginToOrangeHRM(driver, "aravind", "@ravindA1");
		Log.pass("Login to Applicaiton Success");
		Log.info("--Completeds Executing Test - validateLoginTest");
 		//Log.endReport("validateLoginTest");
	}
	
		
	@AfterMethod
	public void tearDown(ITestResult testResult) throws IOException {
		Log.info("inside after method with " +testResult.getStatus());
		Log.info(String.valueOf(ITestResult.FAILURE));
		if (testResult.getStatus() == ITestResult.FAILURE) {
			String path = Screenshots.takeScreenshot(driver, testResult.getName()+ExtentReportFactory.getCurrentDateAndTime());
			Log.info("Path " + path);
			Log.attachScreenShot(path);
		}
		
		Log.endReport();
	}
	
	@AfterClass
	public void tearDown(){
		driver.close();
		driver = null;
	}
}
