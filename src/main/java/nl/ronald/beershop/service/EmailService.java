package nl.ronald.beershop.service;

import javax.mail.MessagingException;
import java.util.List;

public interface EmailService {
    void sendMessageWithAttachment(String from, String to, String title, String body) throws MessagingException;
    void sendSimpleMessage(String to, String subject, String text);
}

