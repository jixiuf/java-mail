package mail.ssl.receive;

import java.security.Security;
import java.util.Properties;

import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;

import mail.MailReceiveInfo;
import mail.MailSendInfo;

public class SimpleMailReceiver implements MailReceiver {
	MailReceiveInfo info = null;
	Store store;

	public void init() {

		Properties props = System.getProperties();

		props.setProperty("mail.pop3.port", "" + info.getMailServerPort());
		props.setProperty("mail.pop3.socketFactory.port", ""
				+ info.getMailServerPort());
	props.setProperty("mail.imap.port", "" + info.getMailServerPort());
		props.setProperty("mail.imap.socketFactory.port", ""
				+ info.getMailServerPort());

		// 以下步骤跟一般的JavaMail操作相同
		Session session = Session.getDefaultInstance(props, null);
		session.setDebug(info.isDebug());
		// 请将红色部分对应替换成你的邮箱帐号和密码
		URLName urln = new URLName(info.getProtocol(),
				info.getMailServerHost(), info.getMailServerPort(), null, info
						.getUserName(), info.getPassword());
		try {
			store = session.getStore(urln);
 		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}

	}

	public SimpleMailReceiver(MailReceiveInfo info) {
		this.info = info;
		init();
	}

	public Store receiver() {
		return store;
	}

}
