package mail.sender;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import mail.MailSendInfo;
import mail.MyAuthenticator;

/**
 * 此类未测试 简单邮件（不带附件的邮件）发送器 http://www.bt285.cn BT下载
 */
public class SimpleMailSender extends AbstractSender {

	protected Message populateCommonInfo(MailSendInfo mailInfo)
			throws MessagingException, FileNotFoundException {
		// 判断是否需要身份认证
		Properties p = System.getProperties();
		p.put("mail.smtp.host", mailInfo.getMailServerHost());
		p.put("mail.smtp.port", mailInfo.getMailServerPort());
		p.put("mail.smtp.auth", "true");

		// 如果需要身份认证，则创建一个密码验证器
		MyAuthenticator authenticator = new MyAuthenticator(
				mailInfo.getUserName(), mailInfo.getPassword());

		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(p, authenticator);
		// 根据session创建一个邮件消息
		sendMailSession.setDebug(mailInfo.isDebug());
		Message mailMessage = null;
		if (mailInfo.getEmailFormatFile() != null) {
			mailMessage = new MimeMessage(sendMailSession);
		} else {
			mailMessage = new MimeMessage(sendMailSession, new FileInputStream(
					mailInfo.getEmailFormatFile()));
		}

		// 创建邮件发送者地址
		Address from = new InternetAddress(mailInfo.getFromAddress());
		// 设置邮件消息的发送者
		mailMessage.setFrom(from);
		// 创建邮件的接收者地址，并设置到邮件消息中
		Address to = new InternetAddress(mailInfo.getToAddress());
		mailMessage.setRecipient(Message.RecipientType.TO, to);
		// 设置邮件消息的主题
		mailMessage.setSubject(mailInfo.getSubject());
		// 设置邮件消息发送的时间
		mailMessage.setSentDate(new Date());
		// 设置邮件消息的主要内容
		return mailMessage;
	}

}