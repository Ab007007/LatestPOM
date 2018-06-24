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
	
	/*public static void sendReportByGMail1(String from, String pass, String to, String subject, String body) {

		// Create object of Property file
		Properties props = new Properties();

		// this will set host of server- you can change based on your requirement 
		props.put("mail.smtp.host", "smtp.gmail.com");

		// set the port of socket factory 
		props.put("mail.smtp.socketFactory.port", "465");

		// set socket factory
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

		// set the authentication to true
		props.put("mail.smtp.auth", "true");

		// set the port of SMTP server
		props.put("mail.smtp.port", "465");
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from,pass);

					}

				});
		try {

			// Create object of MimeMessage class
			Message message = new MimeMessage(session);
			// Set the from address
			message.setFrom(new InternetAddress(from));
			// Set the recipient address
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
			// Add the subject link
			message.setSubject("Testing Subject");
			// Create object to add multimedia type content
			BodyPart messageBodyPart1 = new MimeBodyPart();
			// Set the body of email
			messageBodyPart1.setText("This is message body");
			// Create another object to add another content
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			// Mention the file which you want to send
			String filename = ExtentReportFactory.getPath();
			// Create data source and pass the filename
			DataSource source = new FileDataSource(filename);
			// set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source));
			// set the file
			messageBodyPart2.setFileName(filename);
			// Create object of MimeMultipart class
			Multipart multipart = new MimeMultipart();
			// add body part 1
			multipart.addBodyPart(messageBodyPart2);
			// add body part 2
			multipart.addBodyPart(messageBodyPart1);
			// set the content
			message.setContent(multipart);
			// finally send the email
			Transport.send(message);
			System.out.println("=====Email Sent=====");

		} catch (MessagingException e) {

			throw new RuntimeException(e);

		}

	}*/

}
