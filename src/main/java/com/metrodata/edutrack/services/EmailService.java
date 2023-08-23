package com.metrodata.edutrack.services;

import com.metrodata.edutrack.entities.models.EmailData;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.File;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    public EmailData sendEmail(EmailData emailData) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom("felixsavero78@gmail.com");
            message.setTo(emailData.getTo());
            message.setSubject(emailData.getSubject());
            message.setText(emailData.getBody());

            mailSender.send(message);
            System.out.println("Mail sent!");
            return emailData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public EmailData sendMailWithHTML(Context context, EmailData emailData) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            String body = templateEngine.process("email-sender", context);

            helper.setFrom("felixsavero78@gmail.com");
            helper.setTo(emailData.getTo());
            helper.setSubject(emailData.getSubject());
            helper.setText(body, true);

            FileSystemResource file = new FileSystemResource(new File("D:\\Gallery\\Images\\Downloads\\FcyM4rWakAIN0KR.jpg"));
            helper.addAttachment("Genshin Wallpaper.jpg", file);

            mailSender.send(message);
            System.out.println("Mail sent!");
            return emailData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public EmailData sendMailHTML(EmailData emailData) {
        Context context = new Context();
        context.setVariable("to", emailData.getTo());
        context.setVariable("subject", emailData.getSubject());
        context.setVariable("body", emailData.getBody());

        sendMailWithHTML(context, emailData);
        return emailData;
    }

}
