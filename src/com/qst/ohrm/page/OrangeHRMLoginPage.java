package com.qst.ohrm.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qst.ohrm.utils.Log;
import com.relevantcodes.extentreports.ExtentTest;

public class OrangeHRMLoginPage {

	//Log log = null;
	@FindBy(id="txtUsername")
	WebElement userNameTextBox;
	
	@FindBy(id="txtPassword")
	WebElement passwordTextBox;
	
	@FindBy(id="btnLogin")
	WebElement loginButton;
	
	@FindBy(xpath="//h1")
	WebElement dashboard;
	
	public void enterUserName(String name){
		Log.info("****Entering UserName " + name);
		userNameTextBox.sendKeys(name);
	}
	
	public void enterPassword(String pwd){
		Log.info("****Entering User Password " + pwd);
		passwordTextBox.sendKeys(pwd);
	}
	
	public void clickOnLoginButton(){
		Log.info("****Clicking on Login Button");
		loginButton.click();
		
	}
	public void loginToOrangeHRM(WebDriver driver, String name,String pwd){
		enterUserName(name);
		enterPassword(pwd);
		clickOnLoginButton();
		try{
			if(dashboard.isDisplayed())
			{
				Log.pass("Login Success");
			}
		}catch(Exception ex){
			Log.writeToText(ex.toString());
//			Log.writeToFailFile(ex.toString());
			Log.fail("Login failed with " + name + " and " + pwd);
			
			
		}
		//Assert.assertTrue(dashboard.isDisplayed());
		//Log.pass("Success login");
	}
	
	public OrangeHRMLoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public OrangeHRMLoginPage(WebDriver driver,ExtentTest test)
	{
		PageFactory.initElements(driver, this);
		
	}
}
