package com.qst.ohrm.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qst.ohrm.page.OrangeDashboardPage;
import com.qst.ohrm.page.OrangeHRMLeavePage;
import com.qst.ohrm.page.OrangeHRMLoginPage;
import com.qst.ohrm.page.PIMAddEmployeePage;
import com.qst.ohrm.utils.ConfigFileReader;
import com.qst.ohrm.utils.DriverUtils;
import com.qst.ohrm.utils.ExtentReportFactory;
import com.qst.ohrm.utils.Log;
import com.qst.ohrm.utils.OrangeHRMUtils;
import com.qst.ohrm.utils.Screenshots;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public class EmployeeTest {
	private static WebDriver driver = null;
	ConfigFileReader configFileReader;
	OrangeHRMLoginPage oLoginpage= null;
	OrangeDashboardPage odashboardpage = null;
	OrangeHRMLeavePage oleavePage = null;
	PIMAddEmployeePage pAddPage = null;
 
	static Exception ex = null;
	
	DriverUtils dUtils=null;
	ExtentReports reports;
	ExtentTest test;
 	@BeforeClass
 	public void setup(){
 		configFileReader = new ConfigFileReader();
 		driver = DriverUtils.getWebDriver();
		oLoginpage = new OrangeHRMLoginPage(driver,test);
		odashboardpage = new OrangeDashboardPage(driver,test);
		oleavePage = new OrangeHRMLeavePage(driver,test);
		pAddPage = new PIMAddEmployeePage(driver);
 		//Log=Logger.getLogger(EmployeeTest.class.getName());
 		
 	}
 	@Test(groups="regression")
 	public void createEmployeeTest(){
 		OrangeHRMUtils.startTest(driver, "createEmployeeTest");
		oLoginpage.loginToOrangeHRM(driver, configFileReader.getUserName(), configFileReader.getPasswordName());
		pAddPage.moveMouseOnLeaveTab();
		pAddPage.clickAddEmployee();
		pAddPage.enterUN("AAAA");
		pAddPage.enterLN("BBBBB");
		pAddPage.saveRecord();
		//pAddPage.validateSuccessMessage();
	
 		
 	}
 	
 	@Test(groups="regression")
 	public void createEmployeeTest1(){
// 		Log.configureReport();   
// 		Log.info("--Started Executing Test - createEmployeeTest");
// 		
// 		Log.info("--Completeds Executing Test - createEmployeeTest");
 		
 	}

	@AfterMethod
	public void tearDown(ITestResult testResult) throws IOException {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			String path = Screenshots.takeScreenshot(driver, testResult.getName()+ExtentReportFactory.getCurrentDateAndTime());
			Log.info("Path " + path);
			Log.attachScreenShot(path);
			Log.writeToFailFile(testResult.getThrowable().toString());
			System.out.println(Exception.class.toString());
			
		}
		
		Log.endReport();
	}
 	@AfterClass
	public void closeDriver(){
		driver.close();
		driver=null;
	}
}
