package sender;

import java.io.File;
import java.io.FileNotFoundException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import mail.MailSendInfo;
import mail.sender.MailSender;
import mail.sender.SSLMailSender;
import mail.sender.SimpleMailSender;

public class TestSimpleSendMail {

	public static void testSendMailSSL() {
		// 这个类主要是设置邮件
		MailSendInfo mailInfo = new MailSendInfo();
		mailInfo.setMailServerHost("smtp.localhost");
		mailInfo.setMailServerPort(25);
		// mailInfo.setValidate(true);
		mailInfo.setUserName("mail1");
		mailInfo.setPassword("mail1");// 您的邮箱密码
		mailInfo.setFromAddress("mail1@localhost");
		mailInfo.setToAddress("zhang3@localhost");
		mailInfo.setSubject("simple send mailby java and sendmail without ssl ");
		mailInfo.setContent("simple  send mail by  java and sendmail without ssl ");
		mailInfo.setDebug(true);

		// 注意这里用的是有SSL验证的Sender
		MailSender sender = new SimpleMailSender();
		try {
			// 发送两次，一次以html格式（此时附件会被发送），一次文本
			// sender.sendTextMail(mailInfo);
			// sender.sendHtmlMail(mailInfo);
			sender.sendTextMail(mailInfo);
			System.out.println("已发送");
		} catch (AddressException e) {
			System.err.println(" 发送失败1");
			e.printStackTrace();
		} catch (MessagingException e) {
			System.err.println(" 发送失败2");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		TestSimpleSendMail.testSendMailSSL();
	}

}
