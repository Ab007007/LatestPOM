package com.qst.ohrm.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qst.ohrm.utils.DriverUtils;
import com.qst.ohrm.utils.Log;
import com.relevantcodes.extentreports.ExtentTest;

public class OrangeDashboardPage {

	WebDriverWait wait = null;
	WebDriver driver = null;
	ExtentTest test = null;
	public static final String textDashBoard =  "Dashboard";
	
	@FindBy(id="welcome")
	WebElement welcomeLink;
	
	@FindBy(xpath="//a[text()='Logout']")
	WebElement logoutLink;
	
	@FindBy(xpath="//h1[text()='Dashboard']")
	WebElement elementDashboard;
	
	
	public void clickOnWelcomeLink(){
		Log.info("clicking on welcome link");
		DriverUtils.getVisibleElement(driver, welcomeLink);
		welcomeLink.click();
	}
	
	
	public void clickOnLogoutLink(){
		wait = new WebDriverWait(driver, 60);
		clickOnWelcomeLink();
		wait.until(ExpectedConditions.visibilityOf(logoutLink));
		logoutLink.click();
	}
	
	
	public OrangeDashboardPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}
	
	public OrangeDashboardPage(WebDriver driver,ExtentTest test)
	{
		PageFactory.initElements(driver, this);
		this.test=test;
		this.driver = driver;
		
	}
	
	public boolean isWelcomeExist(){
		boolean flag = false ; 
		try{
			if(welcomeLink.isDisplayed())
				flag = true;
			else
				flag = false;
		}
		catch(Exception ex){
				flag = false;
		}
		return flag;
	}
	
	public void validateDashboardText(WebElement ele){
		Log.info("---validate Dashboard text---");
		wait.until(ExpectedConditions.textToBePresentInElement(ele, textDashBoard));
		if(ele.getText().equals(textDashBoard))
			Log.pass("Validation passed");
		else
			Log.fail("validation Failed");
		Log.info("Validation completed succesfully");
	}
}
