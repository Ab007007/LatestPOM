package com.qst.ohrm.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qst.ohrm.utils.DriverUtils;
import com.qst.ohrm.utils.Log;
import com.relevantcodes.extentreports.ExtentTest;

public class OrangeHRMLeavePage {

	public OrangeHRMLeavePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public OrangeHRMLeavePage(WebDriver driver,ExtentTest test)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	Log log = null;
	WebDriver driver = null;
	WebDriverWait wait =null;
	
	@FindBy(id="menu_leave_viewLeaveModule")
	WebElement leaveTab;
	
	@FindBy(id="menu_leave_viewLeaveList")
	WebElement leaveList;

//	@FindBy(xpath="//label[text()='From']/following-sibling::img")
//	WebElement fromCalIcon;
//	
//	@FindBy(xpath="//label[text()='To']/following-sibling::img")
//	WebElement toCalIcon;
//	
	@FindBy(id="leaveList_chkSearchFilter_checkboxgroup_allcheck")
	WebElement allCheckbox;
	
	@FindBy(id="leaveList_txtEmployee_empName")
	WebElement employeeTextBox;
	
	@FindBy(id="leaveList_cmbSubunit")
	WebElement subUnitDropdown;
	
	@FindBy(id="btnSearch")
	WebElement searchButton;
	
//	@FindBy(xpath="//h1[text()='%s']")
//	WebElement pagetitle;

	
	private WebElement pagetitle(String pageText){
		return driver.findElement(By.xpath(String.format("//h1[text()='%s']", pageText)));
	}
		

	@FindBy(id="calFromDate")
	WebElement calFromDate;
	
	@FindBy(id="calToDate")
	WebElement calToDate;
	
	@FindBy(id="resultTable")
	WebElement resultTable;
	
	
	
	public void moveMouseOnLeaveTab(){
		Log.info("Moving mouse to List Tab " );
		Actions act = new Actions(driver);
		act.moveToElement(leaveTab).perform();
		DriverUtils.getVisibleElement(driver,leaveList);
		Assert.assertTrue(leaveList.isDisplayed());
		
	}
	
	public void clickOnleaveList(){
		Log.info("---clicking on leave list---");
		leaveList.click();
		DriverUtils.getVisibleElement(driver, pagetitle("Leave List1"));
		Assert.assertTrue(pagetitle("Leave List1").isDisplayed());
		Log.pass("*** Click on leave list success***");
	}
	/**
	 * @author ahb
	 * @param yyyy_mm_dd - please enter the date in yyyy-mm-dd format only
	 */
	public void enterFromDate(String yyyy_mm_dd)
	{
		Log.info("--- Entering date--- " + yyyy_mm_dd);
		calFromDate.clear();
		calFromDate.sendKeys(yyyy_mm_dd);
		
	}
	
	public void enterToDate(String yyyy_mm_dd){
		Log.info("---Entering to date ---" + yyyy_mm_dd);
		calToDate.clear();
		calToDate.sendKeys(yyyy_mm_dd);
	}
	
	public void selectAllLeaveStatusCheckBox(){
		Log.info("---Selecting All Check box ---");
		DriverUtils.selectCheckBox(allCheckbox, "true");
	}
	
	public void enterEmployeeName(String un){
		Log.info("--- Entering Employee name---"+ un);
		employeeTextBox.clear();
		employeeTextBox.sendKeys(un);
	}
	
	public void clickOnSearchButton(){
		Log.info("---Click on search button---");
		searchButton.click();
		DriverUtils.getVisibleElement(driver, resultTable);
		
		Assert.assertTrue(resultTable.isDisplayed());
	}
	
	
	
}
