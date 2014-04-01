package cn.frank.foundation.networkTest;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSendMain {

	public static void main(String[] args) throws IOException{

		//JavaMail Session store configuration
		final Properties p = new Properties();	
		p.load(MailSendMain.class.getResourceAsStream("smtp.properties"));
		Session session = Session.getInstance(p, new Authenticator(){
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(p.getProperty("mail.user"),p.getProperty("mail.user.passwd"));
			}
		});
		
		try {
			//Message for email content
			Message msg = new MimeMessage(session);
			msg.addRecipient(RecipientType.TO, new InternetAddress("jfan_sh@sina.com","Jun SH Fan"));
			//test one error recipient, sent OK also
			msg.addRecipient(RecipientType.TO, new InternetAddress("jfan_sh@sina.comm","Jun SH Fan"));
			msg.addRecipient(RecipientType.CC, new InternetAddress("fanjun301@126.com","Frank Fan"));

			msg.setSubject("java smtp api 中文");
			
			MimeMultipart mp = new MimeMultipart();  
            //add html
			MimeBodyPart mbp = new MimeBodyPart();
            mbp.setContent("<a href=\"http://www.baidu.com\" target=\"_blank\">链接</a>","text/html;charset=utf-8");
            mp.addBodyPart(mbp);
            //add inputstream content.
            MimeBodyPart mbp1 = new MimeBodyPart(MailSendMain.class.getResourceAsStream("content.txt"));
            mp.addBodyPart(mbp1);
            //add attachement
            MimeBodyPart mbp2 = new MimeBodyPart();
            mbp2.attachFile(MailSendMain.class.getResource(".").getPath()+"content.txt");
            mp.addBodyPart(mbp2);
            
            msg.setContent(mp);
			msg.saveChanges();
			Transport transport = session.getTransport("smtp");
			transport.connect();
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
