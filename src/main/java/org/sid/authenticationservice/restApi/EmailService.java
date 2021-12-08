package org.sid.authenticationservice.restApi;

import org.sid.authenticationservice.models.Contact;
import org.sid.authenticationservice.models.NaturalPerson;
import org.sid.authenticationservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;


    void sendEmail(User user) {

        System.out.println("Emaaaaaaaaaaaaaaaaail"+user.getEmail());

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getEmail());

        msg.setSubject("Bienvenue à RIZIK Banque");
        msg.setText("Bonjour \n Votre login : "+user.getUsername()+
                "\n Votre mot de passe est  :"+user.getPassword());

        javaMailSender.send(msg);

    }
}