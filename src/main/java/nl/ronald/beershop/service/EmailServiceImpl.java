package nl.ronald.beershop.service;

import nl.ronald.beershop.exception.BadRequestException;
import nl.ronald.beershop.model.Customer;
import nl.ronald.beershop.payload.ConfirmationRequest;
import nl.ronald.beershop.payload.SimpleEmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@beershop.nl");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void sendConfirmation(ConfirmationRequest request) {
        try {
            MimeMessage message = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            String content = "<h2>Bevestiging bestelling</h2>" +
                    "Geachte " + request.getFirstname() + ", <br /><br /> Hieronder vind je jouw bestelling:<br />" +
                    "<div>Jouw bestelling</h2></b></td><td>&nbsp;</td></tr>" +
                    "<table style='width: 500px; background-color:#fcfcfc; border: 1px solid #eee; padding: 20px;'><tbody>" +
                    "<tr><td style='padding: 10px;'><b>Besteld:</b></td><td>&nbsp;</td><td>&nbsp;</td></tr>" +
                    "<tr><td style='padding: 10px;'>" +request.getName()+ "</td><td>" +request.getAmount()+ " x</td><td>" +request.getTotalPrice()+"</td></tr>" +
                    "<tr><td style='padding: 10px;' colspan='3'><h3>Adresgegevens</h3></td></tr>" +
                    "<tr><td style='padding: 10px;'>"+request.getFirstname()+" "+request.getLastname()+"</td></tr>" +
                    "<tr><td style='padding: 10px;'>"+request.getStreet()+" "+request.getStreetAdd()+" "+request.getNumber()+" </td></tr>" +
                    "<tr><td style='padding: 10px;'>"+request.getPostalCode()+" "+request.getCity()+" </td></tr>" +
                    "<tr><td style='padding: 10px;'>"+request.getCountry()+" </td></tr>" +
                    "</tbody></table>";

            helper.setFrom("noreply@beershop.nl");
            helper.setTo(request.getEmail());
            helper.setSubject("Jouw bestelling #" + request.getId() + "-2021 bij beershop.nl");
            helper.setText(content, true);

            emailSender.send(message);
        } catch(Exception ex) {
            throw new BadRequestException();
        }
    }

    public void sendContactForm(SimpleEmailRequest request) {
        try {
            MimeMessage message = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            String content = "<h2>Ingezonden contactformulier</h2>" +
                    "<table style='width: 500px; background-color:#fcfcfc; border: 1px solid #eee; padding: 20px;'><tbody>" +
                    "<tr><td style='padding: 10px;' colspan='2'><b><h2 style='color: #FFA303;'>Gegevens persoon</h2></b></td><td>&nbsp;</td></tr>" +
                    "<tr><td style='padding: 10px;'><b>Voornaam</b></td><td style='padding: 10px;'>" + request.getFirstname() +"</td></tr>" +
                    "<tr><td style='padding: 10px;'><b>Achternaam</b></td><td style='padding: 10px;'>"+request.getLastname()+"</td></tr>" +
                    "<tr><td style='padding: 10px;'><b>E-mail</b></td><td style='padding: 10px;'>"+request.getEmail()+"</td></tr>" +
                    "<tr><td style='padding: 10px;'><b>Bericht</b></td><td>&nbsp;</td></tr>" +
                    "<tr><td colspan='2' style='padding: 10px;'>"+request.getMessage()+"</td></tr></tbody></table>";

            helper.setFrom("noreply@beershop.nl");
            helper.setTo("ronald.eijsden@gmail.com");
            helper.setSubject("Contact");
            helper.setCc(request.getEmail());
            helper.setText(content, true);

            emailSender.send(message);
        } catch(Exception ex) {
            throw new BadRequestException();
        }
    }

    public void sendMessageWithAttachment(
            String to, String subject, String text, String pathToAttachment) throws MessagingException {

            MimeMessage message = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("noreply@beershop.nl");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            FileSystemResource file
                    = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment("Invoice", file);

            emailSender.send(message);
    }
}