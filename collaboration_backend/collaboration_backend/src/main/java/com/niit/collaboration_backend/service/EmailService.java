package com.niit.collaboration_backend.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.niit.collaboration_backend.model.User;

@Service("emailService")
public class EmailService {
	
	// Autowired the mail sender bean here

	@Autowired
	private JavaMailSender mailSender;
	
	// email name which is not similar to the username
	private static String from = "CommunEX";
	
	/**
	 * approvedUserMessage method will be called using emailService that can be Autowired
	 * args - User 
	 * requires the user object to fetch the email and other content of the user to send the email   
	 * */
	public void approvedUserMessage(User user){
	   	   
	    // Mime message is used to send email like an HTML page	    
	    MimeMessage mimeMessage = mailSender.createMimeMessage();
	    
	    MimeMessageHelper helper = null;
	    
		try {
		
			// instantiating the helper and attaching it with mimeMessage
			helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

			// set up your HTML message here
			StringBuilder htmlMsg = new StringBuilder();
			
			htmlMsg.append("<h1>Welcome " + user.getFirstName()+ " " + user.getLastName() + " on CommunEX!</h1>");
			htmlMsg.append("<p>Your account has been activated!</p><br/>");
			htmlMsg.append("<p>Thanks for joining with us!</p><br/>");		
			
			// add the HTML content to the mimeMessage 
		    mimeMessage.setContent(htmlMsg.toString(), "text/html");
		    
		    // set the subject and recipient of the email
		    helper.setTo(user.getEmailId());
		    helper.setSubject("WELCOME TO CommunEX");
		    helper.setFrom(from);
		    
		    // send the message
		    mailSender.send(mimeMessage);
		    
		} catch (MessagingException e) {
			e.printStackTrace();
		}	
	}
}