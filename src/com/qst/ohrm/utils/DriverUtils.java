package com.qst.ohrm.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.net.UrlChecker.TimeoutException;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;


public class DriverUtils {
	static Log log;
	static ConfigFileReader configFileReader;
	static WebDriver driver;
	static String nodeUrl = null;
	public static WebDriver getWebDriver() {
		configFileReader= new ConfigFileReader();
		System.setProperty("webdriver.gecko.driver", configFileReader.getDriverPath());
		log.info("creating webDriver---");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		log.info("returning webdriver to test");
		return driver;
		
	}
	
	/**
	 * 
	 * @param type - type of the broswer - FF,IE,CHROME
	 * @return
	 */
	public static WebDriver getWebDriver(String type) {
	//	configFileReader= new ConfigFileReader();
		Log.info("Creating a Driver with " + type);
		driver = null;
		switch(type) {
		
		case "FF" :
			log.info("Creating Firefox driver..");
			System.setProperty("webdriver.gecko.driver", "C:\\SeleniumBatch15\\drivers\\geckodriver.exe");
			//Creating WebDriver instance
			driver = new FirefoxDriver();
			break;
		case "IE" :
			log.info("Creating IE Driver ...");
			System.setProperty("webdriver.ie.driver", "C:\\seleniumBatch15\\drivers\\IEDriverServer_Win32_3.9.0\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;
		case "CHROME" :
			log.info("Creating chrome Driver...");
			System.setProperty("webdriver.chrome.driver", "C:\\seleniumBatch15\\drivers\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		
			default :
				log.info("No matching driver available..");
				driver = null;
			
		}
		Log.info("Configuring driver with implicit wait of 30 sec ");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		Log.info("Maximizing the driver/Browser ");
		driver.manage().window().maximize();
		return driver;
		
		
		
	}
	
	public static WebDriver getRemoteFFDriver(){
		//configFileReader= new ConfigFileReader();
		nodeUrl=configFileReader.getNodeUrl(); // url of node
		
		FirefoxOptions options = new FirefoxOptions();
		options.addPreference("browserName", "firefox");
		options.addPreference("browserversion", "20.0");
		options.addPreference("network.proxy.type", 0);
		options.setAcceptInsecureCerts(true);
		
		
		try {
			driver = new RemoteWebDriver(new URL(nodeUrl),options);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return driver;
	}
	
	public static WebDriver getRemoteChromeDriver(){
		//configFileReader= new ConfigFileReader();
		nodeUrl=configFileReader.getNodeUrl(); // url of node

		ChromeOptions options = new ChromeOptions();
		options.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
		options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	
		
		try {
			driver = new RemoteWebDriver(new URL(nodeUrl),options);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return driver;
	}
	
	public static void selectDropDownItem(WebElement ele,String type,String typeValue) {
		Select sel = new Select(ele);
		Log.info("selecting a dropdown value using " + type  + "  and value to be selected is " + typeValue);
		switch(type){
		
		case "byIndex" : 
			sel.selectByIndex(Integer.valueOf(typeValue));
			break;
		case "byValue" :
			sel.selectByValue(typeValue);
			break;
		case "byVisibleText":
			sel.selectByVisibleText(typeValue);
			break;
		}
		
	}
	
	
	/**
	 * 
	 * @param id
	 * @param idValue
	 * @param flag - if true it selects the checkbox , if false unselectes the checkbox
	 */
	public static void selectCheckBox(WebElement checkBox, String flag) {
		Log.info("--function to click on checkbox");
		WebDriverWait wait =  new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.elementToBeClickable(checkBox));
		Log.info("waited successfully for the element to be clickable");
		switch(flag) {
		case "true" : 
			if(!(checkBox.isSelected())) {
				Log.info("Checkbox is not selected .. clicking on checkbox");
				checkBox.click();
			}
			else {
				Log.info("checkbox is already Selected.. so, not clicking on Checkbox");
			}
			break;
		case "false" :
			if(checkBox.isSelected()) {
				Log.info("Checkbox is selected .. clicking on checkbox");
				checkBox.click();
			}
			else {
				Log.info("checkbox is not Selected.. so, not clicking on Checkbox");
			}
		}
	}
	
	public static void validateTextOnScreen(WebElement ele , String expectedTextOnScreen) {
		Log.info("Validating Text on the Screen...");
		WebDriverWait wait =  new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOf(ele));
		Log.info("waited successfully for the element to be visible");
		//getText() is a function on WebElement which returns the text
		//displayed on the screen.
		String actualTextOnScreen = ele.getText();
		if(actualTextOnScreen.equals(expectedTextOnScreen)) {
			Log.info("PASS : Text on the screen is matched");
		}
		else{
			Log.info("FAIL : Text on the screen is Failed to match");
		}
		
	}
	
	public static WebElement getVisibleElement(WebDriver driver, WebElement ele) {
	//	System.out.println("Getting element By " + type  + "-" + value);
		WebDriverWait wait = null;
		try{
			wait = new WebDriverWait(driver, 65);
			wait.until(ExpectedConditions.visibilityOf(ele));
		}catch(Exception ex){
			Log.info("----getVisiblieElement---" + ex.getMessage());
			Log.writeToFailFile(ex.toString());
		}
		return ele;	
		
	}
	
	public static WebElement getClickableElement(WebDriver driver, WebElement ele) {
		//	System.out.println("Getting element By " + type  + "-" + value);
			WebDriverWait wait = null;
			try{
				wait = new WebDriverWait(driver, 65);
				wait.until(ExpectedConditions.elementToBeClickable(ele));
			}catch(Exception ex){
				Log.info("----getClickableElement---" + ex.getMessage());
				Log.writeToFailFile(ex.toString());
			}
			return ele;	
			
		}
	
	
	public static void waitForFluentVisible(WebElement ele){
		Log.info("---");
		FluentWait<WebElement> fWait = new FluentWait<WebElement>(ele)
				
				.withTimeout(Duration.ofSeconds(100))
				.pollingEvery(Duration.ofMillis(100))
				.ignoring(NoSuchElementException.class)
				.ignoring(TimeoutException.class);
		
		fWait.until(new Function<WebElement, Boolean>() {
			
			public Boolean apply(WebElement ele) {
				System.out.println("waiting .....");
				if(ele.isDisplayed()) {
					System.out.println(ele.getText());
					System.out.println("element found... ");
						return true;
				}
				else
					System.out.println("element not found...");
					return false;
			}
			
		});
		
		System.out.println("wait ended");
		
	}
	
}
