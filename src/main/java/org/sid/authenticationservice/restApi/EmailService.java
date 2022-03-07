package org.sid.authenticationservice.restApi;
import org.sid.authenticationservice.models.Account;
import org.sid.authenticationservice.models.User;
import org.sid.authenticationservice.models.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private AccountController ac;


    void sendEmail(User user) {

        System.out.println("Emaaaaaaaaaaaaaaaaail"+user.getEmail());

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getEmail());

        msg.setSubject("Bienvenue à RIZIK Banque");
        msg.setText("Bonjour \n Votre login : "+user.getUsername()+
                "\n Votre mot de passe est  :"+user.getPassword());

        javaMailSender.send(msg);

    }


    void sendEmailAccount(User user) {

        Optional<Account> account= ac.findByUser(user.getId());
        System.out.println("Emaaaaaaaaaaaaaaaaail "+user.getEmail());

        System.out.println("Riiiiiiiiiiiiiiiiiiiiiib spriing  "+account.get().getRib());



        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getEmail());

        msg.setSubject("Bienvenue à RIZIK Banque");

        msg.setText("Bonjour \n Votre RIB est  : "+account.get().getRib());

        javaMailSender.send(msg);

    }



    void sendEmailRefuse(User user) {

        System.out.println("Emaaaaaaaaaaaaaaaaail"+user.getEmail());

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getEmail());

        msg.setSubject("Bienvenue à RIZIK Banque");
        msg.setText("Bonjour \n Nous sommes désolés nous nous pouvons pas vous accorder un accès!\n Vos informations sont incomplètes! Réessayez de nouveau : ");

        javaMailSender.send(msg);

    }



}