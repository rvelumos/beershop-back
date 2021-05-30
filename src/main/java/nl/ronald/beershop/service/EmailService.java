package nl.ronald.beershop.service;


import nl.ronald.beershop.payload.ConfirmationRequest;
import nl.ronald.beershop.payload.NewsLetterRequest;
import nl.ronald.beershop.payload.SimpleEmailRequest;

import javax.mail.MessagingException;

public interface EmailService {
    void sendMessageWithAttachment(String from, String to, String title, String body) throws MessagingException;
    void sendSimpleMessage(String to, String subject, String text);
    void sendContactForm(SimpleEmailRequest request);
    void sendBulkMail(NewsLetterRequest request);
    void sendAccountConfirmation(ConfirmationRequest request);
    void sendConfirmation(ConfirmationRequest request);
}

