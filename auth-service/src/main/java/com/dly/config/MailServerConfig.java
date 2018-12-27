package com.dly.config;

import java.util.Properties;

import com.dly.auth.model.filter.MailSendInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:发送邮件的基本信息配置类
 *
 * @author yxr
 *
 */

@Configuration
public class MailServerConfig {
    // 后面需要改成从配置文件读取
    // 发件箱
    @Value("852346546@qq.com")
    private String fromEmail;
    // 发送邮件的主机为
    @Value("smtp.qq.com")
    private String host;
    // 发件箱账号（用户认证用）
    @Value("852346546@qq.com")
    private String fromEmailAccount;
    // 发件箱授权码（用户认证用）
    @Value("mzgxofacnkhnbcab")
    private String fromEmailAuthCode;

    @Bean
    public MailSendInfo creatMailSendInfo() {
        MailSendInfo mailSendInfo = new MailSendInfo();
        mailSendInfo.setFromEmail(fromEmail);
        mailSendInfo.setFromEmailAccount(fromEmailAccount);
        mailSendInfo.setFromEmailAuthCode(fromEmailAuthCode);

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");

        mailSendInfo.setProperties(properties);

        return mailSendInfo;
    }

}