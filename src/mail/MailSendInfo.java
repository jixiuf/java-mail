package mail;

import java.io.File;

public class MailSendInfo {
	// 发送邮件的服务器的IP和端口
	private String mailServerHost;
	private boolean debug = false;

	private int mailServerPort = 25;
	// 邮件发送者的地址
	private String fromAddress = "";
	// 邮件接收者的地址
	private String toAddress;
	// 登陆邮件发送服务器的用户名和密码
	private String userName;
	private String password;

	// 邮件主题
	private String subject = "";
	// 邮件的文本内容
	private String content = "";
	//从此文件解析成为MiMeMessage,设此则content内容会被忽略
	private File emailFormatFile = null;

	public File getEmailFormatFile() {
		return emailFormatFile;
	}

	public void setEmailFormatFile(File emailFormatFile) {
		this.emailFormatFile = emailFormatFile;
	}

	// 邮件附件的文件名
	private File[] attachFileNames = new File[] {};

	public String getMailServerHost() {
		return mailServerHost;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
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

	public File[] getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(File[] attachFileNames) {
		this.attachFileNames = attachFileNames;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String textContent) {
		this.content = textContent;
	}
}
