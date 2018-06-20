package com.qst.ohrm.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qst.ohrm.utils.ConfigFileReader;
import com.qst.ohrm.utils.Log;


public class EmployeeTest {
	private static WebDriver driver = null;
	ConfigFileReader configFileReader;
	
 
 	@BeforeClass
 	public void setup(){
 		
 		configFileReader= new ConfigFileReader();
		System.setProperty("webdriver.gecko.driver", configFileReader.getDriverPath());
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
 		//Log=Logger.getLogger(EmployeeTest.class.getName());
 		
 	}
 	@Test(groups="regression")
 	public void createEmployeeTest(){
 		Log.configureReport();
		Log.info("New driver instantiated");
 		Log.info("--Started Executing Test - createEmployeeTest");

 		Log.info("--Completeds Executing Test - createEmployeeTest");
 		
 	      
 		
 	}
 	
 	@Test(groups="regression")
 	public void createEmployeeTest1(){
 		Log.configureReport();   
 		Log.info("--Started Executing Test - createEmployeeTest");
 		
 		Log.info("--Completeds Executing Test - createEmployeeTest");
 		
 	}

}
