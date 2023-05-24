package mailcommon;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;

import bean.Email;

public class GuiEmail {
	public static void send(Email email) throws Exception {
		Properties prop = new Properties();
		
		prop.put("mail.smtp.host","smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth","true");
		prop.put("mail.smtp.starttls.enable", "true");
		
		
		Session session = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email.getGuiTu(), email.getGuiMatKhau());
			}
		});
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email.getGuiTu()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getGuiDen()));
			message.setSubject(email.getTieuDeMail(),"UTF-8");
			message.setContent(email.getNoiDung(),"text/html;charset=UTF-8");
			
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
}
