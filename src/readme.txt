使用方法
只须用到MailSender 和MailSenderInfo 两个类

而根据是否SSL安全连接，MailSender 有两个实现 SimpleMailSender ,SSLMailSender ,这两个类可以直接new 
MailSender.sentMethod(MailSenderInfo);

