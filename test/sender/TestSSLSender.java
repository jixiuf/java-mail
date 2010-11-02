package sender;

import java.io.File;
import java.io.FileNotFoundException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.w3c.dom.ls.LSParser;

import mail.MailSendInfo;
import mail.sender.MailSender;
import mail.sender.SSLMailSender;

//注意好像qq 不能接收txt 格式 的附件 ， 其他格式的可以，不知问题出在哪
public class TestSSLSender {
	public static void testGMailSender() {
		// 这个类主要是设置邮件
		MailSendInfo mailInfo = new MailSendInfo();
		mailInfo.setMailServerHost("smtp.gmail.com");
		mailInfo.setMailServerPort(465);
		// mailInfo.setValidate(true);
		mailInfo.setUserName("hack1989huang");
		mailInfo.setPassword("zhao2170!!!!");// 您的邮箱密码
		mailInfo.setFromAddress("hack1989huang@gmail.com");
		mailInfo.setToAddress("hack1989huang@gmail.com");
		mailInfo.setSubject("设置邮箱标题 如http://www.guihua.org 中国桂花网");
		mailInfo.setEmailFormatFile(new File("/tmp/a.eml"));
		mailInfo.setContent("设置邮箱内容  =");
		// File f1 = new File("d:\\text.txt");
		// File f2 = new File("d:\\text.rar");
		// // File f3 = new File("d:\\1.jpg");
		// mailInfo.setAttachFileNames(new File[] { f1, f2, f3 });

		// 注意这里用的是有SSL验证的Sender
		MailSender sender = new SSLMailSender();
		try {// 发送两次，一次以html格式（此时附件会被发送），一次文本
				// sender.sendTextMail(mailInfo);
			sender.sendHtmlMail(mailInfo);
			System.out.println("邮件已发送");
		} catch (AddressException e) {
			System.err.println("发送失败");
			e.printStackTrace();
		} catch (MessagingException e) {
			System.err.println("发送失败");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void testQQSender() {
		// 这个类主要是设置邮件
		MailSendInfo mailInfo = new MailSendInfo();
		mailInfo.setMailServerHost("smtp.qq.com");
		mailInfo.setMailServerPort(465);
		// mailInfo.setValidate(true);
		mailInfo.setUserName("jixiuf");
		mailInfo.setPassword("zhao2170____");// 您的邮箱密码
		mailInfo.setFromAddress("jixiuf@qq.com");
		mailInfo.setToAddress("jixiuf@qq.com");
		mailInfo.setSubject("qq javamail 中车465 ssl ");
		mailInfo.setContent("qq javamail中文 465 ssl ,just a test");
		mailInfo.setEmailFormatFile(new File("/tmp/a.eml"));
		// File f1 = new File("d:\\text.txt");
		// mailInfo.setAttachFileNames(new File[] { f1 });
		// 注意这里用的是有SSL验证的Sender
		MailSender sender = new SSLMailSender();
		try {
			// 发送两次，一次以html格式（此时附件会被发送），一次文本
			// sender.sendTextMail(mailInfo);
			sender.sendHtmlMail(mailInfo);
			System.out.println("qq邮件已发送");
		} catch (AddressException e) {
			System.err.println("qq发送失败");
			e.printStackTrace();
		} catch (MessagingException e) {
			System.err.println("qq发送失败");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public static void testLocalhostSender() {
		// 这个类主要是设置邮件
		MailSendInfo mailInfo = new MailSendInfo();
		mailInfo.setDebug(true);
		mailInfo.setMailServerHost("smtp.localhost");
		mailInfo.setMailServerPort(587);
		// mailInfo.setValidate(true);
		mailInfo.setUserName("mail1");
		mailInfo.setPassword("mail1");// 您的邮箱密码
		mailInfo.setFromAddress("mail1@localhost");
		mailInfo.setToAddress("mail1@localhost");
		mailInfo.setSubject("ssl sendmail localhost 中 ");
		mailInfo.setContent("ssl sendmail localhost 中 ");
		File f1 = new File("/tmp/text.txt");
		mailInfo.setAttachFileNames(new File[] { f1 });
		// 注意这里用的是有SSL验证的Sender
		MailSender sender = new SSLMailSender();
		try {
			// 发送两次，一次以html格式（此时附件会被发送），一次文本
			// sender.sendTextMail(mailInfo);
			sender.sendHtmlMail(mailInfo);
			System.out.println("localhost ssl邮件已发送");
		} catch (AddressException e) {
			System.err.println("localhost ssl Failed");
			e.printStackTrace();
		} catch (MessagingException e) {
			System.err.println("localhost ssl send failed");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TestSSLSender.testQQSender();
		// TestSSLSender.testGMailSender();
		// testLocalhostSender();
	}

}
