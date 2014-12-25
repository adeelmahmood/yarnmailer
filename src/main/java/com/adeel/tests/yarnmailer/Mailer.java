package com.adeel.tests.yarnmailer;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.hadoop.net.NetUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adeel.tests.yarnmailer.utils.YarnUtils;

public class Mailer {

	private static final Logger log = LoggerFactory.getLogger(Mailer.class);
	
	public static void main(String[] args) {
		String to = "aq728y@att.com";
		String from = "aq728y@att.com";
		
		String host = "localhost";
		
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", host);
		
		Session session = Session.getDefaultInstance(props);
		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			message.setSubject("from yarnmailer");
			
			String msg = NetUtils.getHostname() + "\n\n";
			msg += YarnUtils.printEnvAndClasspath();
			message.setText(msg);
			
			Transport.send(message);
			System.out.println("message sent");
		}
		catch(MessagingException e){
			log.error("error in email", e);
			System.exit(1);
		}
		System.exit(0);
	}
}
