package org.jixiuf.exec;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Store;
import javax.mail.Flags.Flag;

import mail.MailReceiveInfo;
import mail.ssl.receive.MailReceiver;
import mail.ssl.receive.SimpleMailReceiver;

import com.sun.mail.imap.IMAPFolder;

public class MailUtil {
	public Store store;

	public MailUtil() {
		getStore();
	}

	private void getStore() {
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
		this.store = store;
	}

	public List<String> getMailContent() throws Exception {

		List<String> mails = new ArrayList<String>();
		IMAPFolder inbox = null;
		try {
			store.connect();
			inbox = (IMAPFolder) store.getFolder("INBOX"); // 以imap的方式打开inbox
			inbox.open(Folder.READ_WRITE);// /--------------------------write
			int unread = inbox.getUnreadMessageCount();
			int all = inbox.getMessageCount();
			Message[] messages = inbox.getMessages(all - unread + 1, all);
			for (int i = 0; i < messages.length; i++) {
				Object o = messages[i].getContent();
				if (o instanceof String) {
					String line = (String) o;
					mails.add(line);
				}
				messages[i].setFlag(Flag.SEEN, true);// 标记此邮件为已读
			}
		} finally {
			inbox.close(false);
			store.close();
		}
		return mails;
	}

	public List<String> getCmds() throws Exception {
		List<String> mails = getMailContent();
		List<String> cmds = new ArrayList<String>();
		for (String mail : mails) {
			cmds.addAll(parseCmd(mail));

		}
		return cmds;
	}

	private List<String> parseCmd(String mailContent) {
		List<String> cmds = new ArrayList<String>();
		String regexp = "\\[\\[(.+?)\\]\\]";
		System.out.println(regexp);
		Pattern p = Pattern.compile(regexp);
		Matcher m = p.matcher(mailContent);
		while (m.find()) {
			 			cmds.add(m.group(1));
		}

		return cmds;
	}

	public static void main(String[] args) {
		// String mailcontent="asdfqwefasfwf<mail@gmail.com>[[hello]][[kitty]]";
		// new MailUtil().parseCmd(mailcontent);
	}
}
