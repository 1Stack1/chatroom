package com.example.server.service;

import javax.mail.MessagingException;

public interface MailService {

    /**
     * 普通文件发送
     */
    void sendSimpleEmail(String email, String subject, String text);

    /**
     * html格式文件发送
     */
    void sendHtmlEmail() throws MessagingException;
}
