package mail;

public class MailReceiveInfo {
	// 发送邮件的服务器的IP和端口
	public final static String PROTOCOL_POP3 = "pop3";
	public final static String PROTOCOL_POP3S = "pop3s";
	public final static String PROTOCOL_IMAP = "imap";
	public final static String PROTOCOL_IMAPS = "imaps";

	private String mailServerHost;
	private String protocol = MailReceiveInfo.PROTOCOL_POP3;
	private int mailServerPort = 110;
	private boolean debug = false;
																
	// 登陆邮件发送服务器的用户名和密码
	private String userName = "";
	private String password = "";

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public int getMailServerPort() {
		return mailServerPort;
	}
		
	public void setMailServerPort(int mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

}
