package com.example.server.service.impl;

import com.example.server.service.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class MailServiceImpl implements MailService {
    @Resource
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;



    /**
     * 普通文件发送
     * @param email 接收方的邮箱
     */
    @Override
    public void sendSimpleEmail(String email,String subject,String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);//配置发送方
        message.setTo(email);//配置接收方
        message.setSubject(subject);//配置标题
        message.setText(text);//配置内容
        mailSender.send(message);
    }


    /**
     * html格式文件发送
     */
    @Override
    public void sendHtmlEmail() throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();//创建一个MINE消息
        MimeMessageHelper minehelper = new MimeMessageHelper(message, true);
        minehelper.setFrom(from);//配置发送方
        minehelper.setTo("1376134353@qq.com");//配置接收方
        minehelper.setSubject("second");//邮件主题
        String content = "<html><body><h1>这是Html格式邮件!,不信你看邮件，我字体比一般字体还要大</h1></body></html>";
        minehelper.setText(content, true);//邮件内容   true 表示带有附件或html
        mailSender.send(message);
    }


}
