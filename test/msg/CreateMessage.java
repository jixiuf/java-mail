package msg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class CreateMessage {
	public void test(Session s) throws MessagingException,
			FileNotFoundException, IOException {
		MimeMessage msg = new MimeMessage(s);
		msg.setFrom(new InternetAddress( "\"lili\" <lili@qq.com>"));
		msg.setReplyTo(InternetAddress.parse("qq <jixiuf@qq.com>,gmail  <jixiuf@gmail.com>"));
		Multipart multipart = new MimeMultipart("mixed");

		BodyPart attatchFile1 = new MimeBodyPart();
		attatchFile1.setDataHandler(new DataHandler(new FileDataSource(
				"attatch.txt")));
		attatchFile1.setFileName(MimeUtility.encodeText("中文") + "hello_.txt");
		multipart.addBodyPart(attatchFile1);

		BodyPart html_jpg = new MimeBodyPart();
		Multipart html_jpg_mul = new MimeMultipart("related");
		multipart.addBodyPart(html_jpg);
		html_jpg.setContent(html_jpg_mul);
		BodyPart html = new MimeBodyPart();
		BodyPart jpg = new MimeBodyPart();
		html_jpg_mul.addBodyPart(html);
		html_jpg_mul.addBodyPart(jpg);

		html.setContent(
				"this is a img from email ,not from the internet <img src='http://url/a.jpg' ></img>",
				"text/html;charset=utf-8");

		jpg.setDataHandler(new DataHandler(new FileDataSource("a.jpg")));
		jpg.setHeader("Content-Location", "http://url/a.jpg");

		msg.setContent(multipart);
		msg.saveChanges();
		FileOutputStream fos = new FileOutputStream(new File("/tmp/a.eml"));
		msg.writeTo(fos);
		fos.close();
		// mainPart.addBodyPart(filePart);
	}

	public static void main(String[] args) throws FileNotFoundException,
			MessagingException, IOException {
		CreateMessage cm = new CreateMessage();
		Session s = Session.getDefaultInstance(new Properties());
		cm.test(s);
	}
}
