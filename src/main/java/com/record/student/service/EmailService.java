package com.record.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    // return new PasswordAuthentication("nileshnarule24@gmail.com", "whftvmvcatyjoaff");

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to,String subject,String message){


        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom("nileshnarule24@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        try {
            
            mailSender.send(simpleMailMessage);

        } catch (Exception e) {
            
            System.out.println("Error in sending email");

        }

    }

}
