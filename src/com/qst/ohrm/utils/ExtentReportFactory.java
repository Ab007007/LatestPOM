package com.qst.ohrm.utils;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReportFactory {
	
	
	
	public static ExtentReports getInstance(){
		 ExtentReports reports ;
		 String path = getReportName();//"reports//RegressionTestResults.html";
		reports = new ExtentReports(path,false);
		return reports;
		
		
	}
			
	public static String getCurrentDateAndTime()
	{
		Date date = new Date();
		String date1 = date.toString().replaceAll(" ", "_").replaceAll(":", "_");
	      // display time and date using toString()
	      System.out.println(date1);
	      
	      return date1;
	}

	public static String getReportName(){
		String name ="reports//report_"+getCurrentDateAndTime()+ ".html";
		return name;
	}
			

	public static String getLogName(String logName){
		String name ="reports//"+logName+ "_"+getCurrentDateAndTime()+ ".txt";
		return name;
	}
}