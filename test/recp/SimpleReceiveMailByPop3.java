package recp;

import java.io.UnsupportedEncodingException;

import javax.mail.FetchProfile;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;

import mail.MailReceiveInfo;
import mail.ssl.receive.MailReceiver;
import mail.ssl.receive.SSLMailReceiver;
import mail.ssl.receive.SimpleMailReceiver;

public class SimpleReceiveMailByPop3 implements parse {
	public static void main(String argv[]) throws Exception {
		MailReceiveInfo info = new MailReceiveInfo();
		info.setMailServerHost("pop.localhost");
		info.setMailServerPort(110);
		info.setProtocol(MailReceiveInfo.PROTOCOL_POP3);
		info.setUserName("mail1");
		info.setPassword("mail1");
		info.setDebug(true);
		MailReceiver receiver = new SimpleMailReceiver(info);
		Store store = receiver.receiver();
		parseMailBox(store);
	}

	private static void parseMailBox(Store store) throws MessagingException,
			UnsupportedEncodingException, AddressException {
		Folder inbox = null;
		try {
			store.connect();
			inbox = store.getFolder("INBOX");//pop3只有这一种名称的forlder ,imap可以有多个folder
			inbox.open(Folder.READ_ONLY);
			FetchProfile profile = new FetchProfile();
			profile.add(FetchProfile.Item.ENVELOPE);
			Message[] messages = inbox.getMessages();
			int count = inbox.getNewMessageCount();//pop3不支持，一直返回0,imap支持
			System.out.println(count);
			inbox.fetch(messages, profile);
			System.out.println("收件箱的邮件数：" + messages.length);
			for (int i = 0; i < messages.length; i++) {
				// 邮件发送者
				String from = decodeText(messages[i].getFrom()[0].toString());
				InternetAddress ia = new InternetAddress(from);
				System.out.println("FROM:" + ia.getPersonal() + '('
						+ ia.getAddress() + ')');
				// 邮件标题
				System.out.println("TITLE:" + messages[i].getSubject());
				// 邮件大小
				System.out.println("SIZE:" + messages[i].getSize());
				// 邮件发送时间
				System.out.println("DATE:" + messages[i].getSentDate());
			}
		} finally {
			try {
				inbox.close(false);
			} catch (Exception e) {
			}
			try {
				store.close();
			} catch (Exception e) {
			}
		}
	}

	protected static String decodeText(String text)
			throws UnsupportedEncodingException {
		if (text == null)
			return null;
		if (text.startsWith("=?GB") || text.startsWith("=?gb"))
			text = MimeUtility.decodeText(text);
		else
			text = new String(text.getBytes("ISO8859_1"));
		return text;
	}
}
