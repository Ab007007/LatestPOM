package com.qst.ohrm.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qst.ohrm.utils.DriverUtils;
import com.qst.ohrm.utils.Log;

public class PIMAddEmployeePage {

	WebDriver driver = null;
	
	@FindBy(id="menu_pim_viewPimModule")
	WebElement pimTab;
	
	@FindBy(id="menu_pim_addEmployee")
	WebElement addEmployee;
	
	@FindBy(id="firstName")
	WebElement firstName;
	@FindBy(id="lastName")
	WebElement lastName;
	@FindBy(id="btnSave")
	WebElement btnSave;
	
	
	@FindBy(xpath="//div[@class='message success fadable']")
	WebElement successmsg;
	
	
	public void moveMouseOnLeaveTab(){
		Log.info("Moving mouse to List Tab " );
		Actions act = new Actions(driver);
		act.moveToElement(pimTab).perform();
		DriverUtils.getVisibleElement(driver,pimTab);
		Assert.assertTrue(addEmployee.isDisplayed());
		
	}
	
	public PIMAddEmployeePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	private WebElement pagetitle(String pageText){
		return driver.findElement(By.xpath(String.format("//h1[text()='%s']", pageText)));
	}
	
	
	public void enterUN(String name){
		Log.info("---Entering UserNAme---");
		firstName.sendKeys(name);
	}
	
	public void enterLN(String name){
		Log.info("---Entering User Last name---");
		lastName.sendKeys(name);
	}
	
	public void saveRecord(){
		Log.info("---clciiking on save button---");
		btnSave.click();
		
	}
	
	public void clickPIM(){
		Log.info("---clciiking on PIM LINK ---");
		pimTab.click();
		
	}
	
	
	public void clickAddEmployee(){
		Log.info("---clciiking on save button---");
		DriverUtils.waitForFluentVisible(addEmployee);
		addEmployee.click();
		
	}
	
	public void validateSuccessMessage(){
		Log.info("---waiting for success message---");
		DriverUtils.waitForFluentVisible(successmsg);
	}
}
