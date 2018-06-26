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

public class ScriptDevelopment {

	ConfigFileReader configFileReader = null;
	WebDriver driver = null;
	OrangeHRMLoginPage oLoginpage= null;
	OrangeDashboardPage odp = null;
	
	@BeforeClass
 	public void preConfig(){
 		configFileReader= new ConfigFileReader();
 		Log.configureReport();
 		Log.startReport("setup");
 		configFileReader= new ConfigFileReader();
		driver = DriverUtils.getWebDriver();
		oLoginpage = new OrangeHRMLoginPage(driver);
		odp = new OrangeDashboardPage(driver);
		
 	}
	@Test(groups={"smoke"})
	public void validateLoginTest(){
		OrangeHRMUtils.startTest(driver, "validateLoginTest");
		oLoginpage.loginToOrangeHRM(driver, configFileReader.getUserName(), configFileReader.getPasswordName());
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
			Log.ssPath.add(path);
			Log.attachScreenShot(path);
		}
		
		Log.endReport();
	}
	
	
	@AfterClass
	public void tearDown(){
		try{
			ExtentReportFactory.sendReportByGMail();
		}catch(Exception ex){
			System.out.println(ex);
		}finally{
			if(driver!=null){
					driver.close();
					driver = null;
			}
		}
	}
}
