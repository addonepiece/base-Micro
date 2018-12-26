package com.dly.auth.util;

import com.dly.auth.model.filter.MailSendInfo;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailThread extends Thread {
    private MailSendInfo mailSendInfo;

    public SendMailThread(MailSendInfo mailSendInfo) {
        this.mailSendInfo = mailSendInfo;
    }

    @Override
    public void run() {
        // 获取默认session对象
        Session session = Session.getDefaultInstance(mailSendInfo.getProperties(), new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailSendInfo.getFromEmailAccount(),
                        mailSendInfo.getFromEmailAuthCode());
            }
        });
        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(mailSendInfo.getFromEmail()));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailSendInfo.getToEmail()));

            // Set Subject: 头部头字段
            message.setSubject(mailSendInfo.getSubject());

            // 发送 HTML 消息, 可以插入html标签
            message.setContent(mailSendInfo.getContent(), "text/html;charset=UTF-8");
            System.out.println(mailSendInfo.getContent());
            // 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....from lz.com");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
