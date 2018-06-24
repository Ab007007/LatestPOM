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
			ExtentReportFactory.sendReportByGMail("selenium15.intellipaat@gmail.com", "Intellipaat@1", "TestExecution-Report", "", "aru03.info@gmail.com","farheenfathima197@gmail.com","sujithkumar969@gmail.com","arunasundaram02@gmail.com","satish.matti@gmail.com","naveen.kkr1@gmail.com","lalithroopa056@gmail.com","kavyakc.1404@gmail.com","sushma.hv@gmail.com");
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
