package com.library.libmanagementsystem.Service.impl;

import com.library.libmanagementsystem.Service.IEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements IEmailService {

    private final JavaMailSender javaMailSender;


    @Override
    public void sendEmail(String to, String subject, String body) {

        try{
            MimeMessage mimeMessage=javaMailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,"utf-8");
            helper.setSubject(subject);
            helper.setText(body,true);
            helper.setTo(to);
            javaMailSender.send(mimeMessage);
        }
        catch(MailException e){
            throw new MailSendException("Failed to send mail ");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
