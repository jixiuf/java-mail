package recp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;
import javax.mail.Flags.Flag;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;

import mail.MailReceiveInfo;
import mail.ssl.receive.MailReceiver;
import mail.ssl.receive.SimpleMailReceiver;

import com.sun.mail.imap.IMAPFolder;

public class SimpleReceiveMailByIMAP implements parse {
	public static void main(String argv[]) throws Exception {
		MailReceiveInfo info = new MailReceiveInfo();
		// info.setMailServerHost("imap.localhost");
		// info.setMailServerPort(143);
		// info.setProtocol(MailReceiveInfo.PROTOCOL_IMAP);
		// info.setUserName("mail1");
		info.setMailServerHost("imap.gmail.com");
		info.setMailServerPort(993);
		info.setProtocol(MailReceiveInfo.PROTOCOL_IMAPS);
		info.setUserName("hack1989huang");
		info.setPassword("zhao2170!!!!");
		info.setDebug(false);
		MailReceiver receiver = new SimpleMailReceiver(info);
		Store store = receiver.receiver();

		parseMailBox(store);
	}

	private static void parseMailBox(Store store) throws MessagingException,
			AddressException, IOException {
		
		IMAPFolder inbox = null;
		try {
			store.connect();
 
			inbox =(IMAPFolder) store.getFolder("INBOX"); // 以imap的方式打开inbox
		 
			inbox.open(Folder.READ_WRITE);// /--------------------------write

			int unread = inbox.getUnreadMessageCount();
			int all = inbox.getMessageCount();

			System.out.println("new+:" + unread);

			System.out.println("all:" + all);
		//	Message[] messages = inbox.getMessages();
				 Message[] messages = inbox.getMessages(all - unread+1, all);

			System.out.println("收件箱的邮件数：" + messages.length);

			for (int i = 0; i < messages.length; i++) {
				print(messages[i]);

				messages[i].setFlag(Flag.SEEN, true);// 标记此邮件为已读
				// messages[i].setFlag(Flags.Flag.DELETED, true);//删除此邮件

			//	inbox.expunge();

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

	public static void print(Message msg) throws MessagingException,
			IOException {
		String from = decodeText(msg.getFrom()[0].toString());
		InternetAddress ia = new InternetAddress(from);
		System.out.println("FROM:" + ia.getPersonal() + '(' + ia.getAddress()
				+ ')');
		// 邮件标题
		System.out.println("TITLE:" + msg.getSubject());
		// 邮件大小
		System.out.println("SIZE:" + msg.getSize());
		// 邮件发送时间
		System.out.println("DATE:" + msg.getSentDate());
		System.out.println("content:" + msg.getContent());
		 InputStream in = msg.getInputStream();
		 InputStreamReader r = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(r);
	String line=br.readLine();
	while(line!=null) {
			System.out.println(line);
			line=br.readLine();
	}
	
		System.out.println("===========================\n\n");
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
