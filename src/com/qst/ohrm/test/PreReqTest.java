package com.qst.ohrm.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.qst.ohrm.utils.ConfigFileReader;
import com.qst.ohrm.utils.ExtentReportFactory;
import com.qst.ohrm.utils.Log;

public class PreReqTest {
	
	ConfigFileReader configFileReader = null;
	WebDriver driver = null;
	@BeforeSuite
 	public void preConfig(){
 		configFileReader= new ConfigFileReader();
 		Log.configureReport();
 		Log.startReport("setup");
		
 	}
	
	
	@AfterSuite
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
