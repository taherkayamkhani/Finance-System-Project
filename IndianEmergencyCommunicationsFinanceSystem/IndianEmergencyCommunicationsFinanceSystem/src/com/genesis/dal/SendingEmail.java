package com.genesis.dal;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendingEmail 
{
	static String senderEmailID="deduplication123@gmail.com",senderPassword="genesis123";
	
	public static void userEmailSending(String receiverEmailID, String emailSubject, String emailBody) 
	{
		final String emailSMTPserver = "smtp.gmail.com";
		final String emailServerPort = "465";

		String v_receiverEmailID=receiverEmailID;
		String v_emailSubject=emailSubject;
		String v_emailBody=emailBody;


		Properties props = new Properties();
		props.put("mail.smtp.user",senderEmailID);
		props.put("mail.smtp.host", emailSMTPserver);
		props.put("mail.smtp.port", emailServerPort);
	//	props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		// props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.socketFactory.port", emailServerPort);
		props.put("mail.smtp.socketFactory.class",
		"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		SecurityManager security = System.getSecurityManager();

		try 
		 {
		       Authenticator auth = new SMTPAuthenticator();
		       Session session = Session.getInstance(props, auth);
		        // session.setDebug(true);

		       MimeMessage msg = new MimeMessage(session);
		       msg.setText(emailBody);
		       msg.setSubject(emailSubject);
		       msg.setFrom(new InternetAddress(senderEmailID));
		       msg.addRecipient(Message.RecipientType.TO,
		       new InternetAddress(receiverEmailID));
		      
		       Transport.send(msg);
		       
		      
		       System.out.println("Message sent sucessfully");
		 } 
		catch (Exception mex) 
		 {
		      mex.printStackTrace();
		      System.out.println("Sending Problem");
		 }
		
    }
	public static void  main(String[] args){
		
		SendingEmail.userEmailSending("kbp.2002@gmail.com","hi" ,"hello") ;
	 }
	
}
class SMTPAuthenticator extends javax.mail.Authenticator
{
   public PasswordAuthentication getPasswordAuthentication() 
   {
     return new PasswordAuthentication(SendingEmail.senderEmailID, SendingEmail.senderPassword);
   }




}