package mail.sender;

import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import mail.MailSendInfo;
import mail.MyAuthenticator;

public class SSLMailSender  extends AbstractSender {
	Properties props = null;
	final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

 

	protected Message populateCommonInfo(MailSendInfo mailInfo)
			throws AddressException, MessagingException {
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		props = System.getProperties();
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.put("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.host", mailInfo.getMailServerHost());

		props.setProperty("mail.smtp.port", ""+mailInfo.getMailServerPort());
		props.setProperty("mail.smtp.socketFactory.port", ""+mailInfo
				.getMailServerPort());

		Session session = Session.getDefaultInstance(props,
				new MyAuthenticator(mailInfo.getUserName(), mailInfo
						.getPassword()));
		session.setDebug(mailInfo.isDebug());

		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(mailInfo.getFromAddress()));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(
				mailInfo.getToAddress(), false));
		msg.setSubject(mailInfo.getSubject());
		msg.setSentDate(new Date());
		return msg;
	}



}
