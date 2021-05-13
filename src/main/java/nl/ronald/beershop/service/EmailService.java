package nl.ronald.beershop.service;

import nl.ronald.beershop.model.Customer;
import nl.ronald.beershop.payload.SimpleEmailRequest;

import javax.mail.MessagingException;
import java.util.List;

public interface EmailService {
    void sendMessageWithAttachment(String from, String to, String title, String body) throws MessagingException;
    void sendSimpleMessage(String to, String subject, String text);
    void sendContactForm(SimpleEmailRequest request);
}

