package nl.ronald.beershop.controller;

import nl.ronald.beershop.model.Index;
import nl.ronald.beershop.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class IndexController {

    @Autowired
    EmailService emailService;

    @PostMapping(value = "/api/v1/forms/contactform")
    public ResponseEntity<Object> sendContactForm(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String email,
            @RequestParam String message)
    {

        String content = "<h2>Ingezonden contactformulier</h2>" +
                "<table><tbody><tr><td><b>Gegevens persoon</b></td><td>&nbsp;</td></tr>" +
                "<tr><td><b>Voornaam</b></td><td>"+firstname+"</td></tr>" +
                "<tr><td><b>Achternaam</b></td><td>"+lastname+"</td></tr>" +
                "<tr><td><b>E-mail</b></td><td>"+email+"</td></tr>" +
                "<tr><td><b>Bericht</b></td><td>"+message+"</td></tr></tbody></table>";

        emailService.sendSimpleMessage("ronald.eijsden@gmail.com", "Contactformulier Beershop", content);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @Bean
//    public JavaMailSender getJavaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.gmail.com");
//        mailSender.setPort(587);
//
//        mailSender.setUsername("beershopproject");
//        mailSender.setPassword("bsp@&__~bier");
//
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true");
//
//        return mailSender;
//    }

}
