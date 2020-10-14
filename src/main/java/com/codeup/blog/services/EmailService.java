package com.codeup.blog.services;

import com.codeup.blog.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailService {
    @Autowired
    public JavaMailSender emailSender;
//    @Value("${spring.mail.from}")
//    private String from;
//    public void prepareAndSendAd(Ad ad, String subject, String body) {
//        SimpleMailMessage msg = new SimpleMailMessage();
//       msg.setFrom(from);
//        msg.setTo(ad.getUser().getEmail());
//        msg.setSubject(subject);
//        msg.setText(body);
//        new Thread(new RunnableEmail(this, msg)).start();
//    }

    public void prepareAndSend(Post to, String subject, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setFrom(from);
        msg.setTo(String.valueOf(to));
        msg.setSubject(subject);
        msg.setText(body);
        new Thread(new RunnableEmail(this, msg)).start();
    }
}
class RunnableEmail implements Runnable {
    private EmailService emailService;
    private SimpleMailMessage msg;
    public RunnableEmail(EmailService emailService, SimpleMailMessage msg) {
        this.emailService = emailService;
        this.msg = msg;
    }
    @Override
    public void run() {
        try{
            emailService.emailSender.send(msg);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }
}