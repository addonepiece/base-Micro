package com.dly.auth.model.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

@Data
@Slf4j
public class MailSendInfo {
    private Properties properties;
    // 发件箱
    private String fromEmail;
    // 发件箱账号（用户认证用）
    private String fromEmailAccount;
    // 发件箱授权码（用户认证用）
    private String fromEmailAuthCode;
    // 收件人电子邮箱
    private String toEmail;
    // 标题
    private String subject;
    // 发送 HTML 消息, 可以插入html标签
    private String content;

}
