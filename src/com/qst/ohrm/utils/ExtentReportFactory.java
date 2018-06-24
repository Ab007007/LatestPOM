package com.qst.ohrm.utils;

import java.util.Date;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReportFactory {

	static String path =null;
	public static ExtentReports getInstance() {
		ExtentReports reports;
		path = getReportName();// "reports//RegressionTestResults.html";
		setPath(path);
		reports = new ExtentReports(path, false);
		return reports;

	}

	public static String getPath() {
		return path;
	}

	public static void setPath(String path) {
		ExtentReportFactory.path = path;
	}

	public static String getCurrentDateAndTime() {
		Date date = new Date();
		String date1 = date.toString().replaceAll(" ", "_").replaceAll(":", "_");
		// display time and date using toString()
		System.out.println(date1);

		return date1;
	}

	public static String getReportName() {
		String name = "reports//report_" + getCurrentDateAndTime() + ".html";
		return name;
	}

	public static String getLogName(String logName) {
		String name = "reports//" + logName + "_" + getCurrentDateAndTime() + ".txt";
		return name;
	}

	/**
	 * 
	 * @param from
	 * @param password
	 * @param to
	 * @param subject
	 * @param body
	 * @throws EmailException 
	 */
	
	public static void sendReportByGMail(String... to) throws EmailException 
	{
		EmailAttachment attachment = new EmailAttachment();
		  attachment.setPath(ExtentReportFactory.getPath());
		  attachment.setDisposition(EmailAttachment.ATTACHMENT);
		  attachment.setDescription("TEST EXECUTION REPORT");
		  attachment.setName(ExtentReportFactory.getPath());

		  // Create the email message
		  MultiPartEmail email = new MultiPartEmail();
		  email.setHostName("smtp.gmail.com");
		  //email.addTo(to, "Aravinda HB");
		  if(to.length==0){
			  email.addTo("aru03.info@gmail.com");
		  }else
		  {
			  email.addTo(to);
		  }
		  email.setAuthenticator(new DefaultAuthenticator("selenium15.intellipaat@gmail.com", "Intellipaat@1"));
		  email.setSSLOnConnect(true);
		  email.setFrom("selenium15.intellipaat@gmail.com", "QSP-OrangeHRM Automation Team");
		  email.setSubject("Automation Test Execution report for OrangeHRM");
		  email.setMsg("Refer the attachment fot the detailed Execution Report");
		  

		  // add the attachment
		  email.attach(attachment);

		  // send the email
		  email.send();
	}
	
	

}
