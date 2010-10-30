package mail.sender;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import mail.MailSendInfo;

public interface MailSender {
	public void sendTextMail(MailSendInfo mailInfo) throws AddressException,
			MessagingException;

	public void sendHtmlMail(MailSendInfo mailInfo) throws AddressException,
			MessagingException;

}
