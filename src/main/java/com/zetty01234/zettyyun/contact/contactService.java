package com.zetty01234.zettyyun.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class contactService {
    @Autowired
    private MailSender mailSender;

    @Value("${spring.mail.username}")
    private String me;

    public void sendEmail(String subject, String message) {
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(me);
        mail.setFrom(me);
        mail.setSubject(subject);
        mail.setText(message);

        mailSender.send(mail);
    }
}
