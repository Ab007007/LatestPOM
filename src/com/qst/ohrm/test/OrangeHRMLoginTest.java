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

public class OrangeHRMLoginTest {

	private static WebDriver driver = null;
	OrangeHRMLoginPage oLoginpage= null;
	OrangeDashboardPage odp = null;
	
	ConfigFileReader configFileReader;
	DriverUtils dUtils=null;
	ExtentReports reports;
	ExtentTest test;
 	
	@BeforeClass
 	public void setup(){
 		configFileReader= new ConfigFileReader();
		driver = DriverUtils.getWebDriver();
		oLoginpage = new OrangeHRMLoginPage(driver,test);
		odp = new OrangeDashboardPage(driver,test);
 	}
 	
	@Test(groups={"smoke"})
	public void validateLoginTest(){
		OrangeHRMUtils.startTest(driver, "validateLoginTest");
		oLoginpage.loginToOrangeHRM(driver, configFileReader.getUserName(), configFileReader.getPasswordName());
			Log.pass("Login to Applicaiton Success");
		Log.info("--Completeds Executing Test - validateLoginTest");
 		//Log.endReport("validateLoginTest");
	}
	
	@Test
	public void validateLoginFailTest(){
		OrangeHRMUtils.startTest(driver, "validateLoginTest");
		oLoginpage.loginToOrangeHRM(driver, configFileReader.getUserName(), configFileReader.getPasswordName() + 1);
		Log.info("completed Successfully " );
 	//	Log.endReport("validateLoginTest1");
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
