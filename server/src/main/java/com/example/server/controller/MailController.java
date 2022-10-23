package com.example.server.controller;


import com.example.server.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
    @Autowired
    private MailService mailService;

    @GetMapping("/sendMail")
    public void sendMail(){
        mailService.sendSimpleEmail("","","");
    }
}
