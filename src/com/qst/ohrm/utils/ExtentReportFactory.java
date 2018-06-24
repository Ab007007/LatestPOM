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
	
	public static void sendReportByGMail(String from, String pass, String to, String subject, String body) throws EmailException 
	{
		EmailAttachment attachment = new EmailAttachment();
		  attachment.setPath(ExtentReportFactory.getPath());
		  attachment.setDisposition(EmailAttachment.ATTACHMENT);
		  attachment.setDescription("TEST EXECUTION REPORT");
		  attachment.setName(ExtentReportFactory.getPath());

		  // Create the email message
		  MultiPartEmail email = new MultiPartEmail();
		  email.setHostName("smtp.gmail.com");
		  email.addTo(to, "Aravinda HB");
		  email.setAuthenticator(new DefaultAuthenticator(from, pass));
		  email.setSSLOnConnect(true);
		  email.setFrom(from, "QSP-OrangeHRM Automation Team");
		  email.setSubject(subject);
		  email.setMsg("Refer the attachment");

		  // add the attachment
		  email.attach(attachment);

		  // send the email
		  email.send();
	}
	
	

}
