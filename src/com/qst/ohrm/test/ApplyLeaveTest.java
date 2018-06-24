package com.qst.ohrm.test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.internal.ITestResultNotifier;

import com.qst.ohrm.page.OrangeDashboardPage;
import com.qst.ohrm.page.OrangeHRMLeavePage;
import com.qst.ohrm.page.OrangeHRMLoginPage;
import com.qst.ohrm.utils.ConfigFileReader;
import com.qst.ohrm.utils.CustomListner;
import com.qst.ohrm.utils.DriverUtils;
import com.qst.ohrm.utils.ExtentReportFactory;
import com.qst.ohrm.utils.Log;
import com.qst.ohrm.utils.OrangeHRMUtils;
import com.qst.ohrm.utils.Screenshots;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import sun.util.logging.resources.logging;
//@Listeners(CustomListner.class)
public class ApplyLeaveTest {

	private static WebDriver driver = null;
	OrangeHRMLoginPage oLoginpage= null;
	OrangeDashboardPage odashboardpage = null;
	OrangeHRMLeavePage oleavePage = null;
	static Exception ex = null;
	
	ConfigFileReader configFileReader;
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
 	}
	
	@Test(groups={"smoke","regression"})
	public void validateLeaveListTest(){
		OrangeHRMUtils.startTest(driver, "validateLeaveListTest");
		oLoginpage.loginToOrangeHRM(driver, configFileReader.getUserName(), configFileReader.getPasswordName());
		
		oleavePage.moveMouseOnLeaveTab();
		oleavePage.clickOnleaveList();
		oleavePage.enterFromDate("2018-01-01");
		oleavePage.enterToDate("2018-12-31");
		oleavePage.selectAllLeaveStatusCheckBox();
		oleavePage.enterEmployeeName("Employee-A LN-1");
		oleavePage.clickOnSearchButton();
		odashboardpage.clickOnLogoutLink();
	}

	@Test 
	public void validateAddEntitelmet(){
		OrangeHRMUtils.startTest(driver, "validateAddEntitelmet");
		oLoginpage.loginToOrangeHRM(driver, configFileReader.getUserName(),configFileReader.getPasswordName());
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
