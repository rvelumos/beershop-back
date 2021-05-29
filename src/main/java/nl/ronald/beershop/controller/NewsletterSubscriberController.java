package nl.ronald.beershop.controller;

import nl.ronald.beershop.model.NewsletterSubscriber;
import nl.ronald.beershop.repository.NewsletterSubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class NewsletterSubscriberController {
    @Autowired
    private NewsletterSubscriberRepository newsletterSubscriberRepository;

    @GetMapping(value="/admin/newsletter/subscribers")
    public ResponseEntity<Object> getNewsletterSubscribers() {
        List<NewsletterSubscriber> newsletterSubscribers = newsletterSubscriberRepository.findAll();
        return new ResponseEntity<>(newsletterSubscribers, HttpStatus.OK);
    }

    @PostMapping(value="/newsletter/subscriber/create")
    public ResponseEntity<Object> createNewsletterSubscriber(@RequestBody NewsletterSubscriber newsletterSubscriber) {
        newsletterSubscriberRepository.save(newsletterSubscriber);
        return new ResponseEntity<>("Succesvol aangemeld", HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/subscriber/{id}")
    public ResponseEntity<Object> deleteNewsletterSubscriber(@PathVariable("id") long id) {
        newsletterSubscriberRepository.deleteById(id);
        return new ResponseEntity<>("Gebruiker verwijderd", HttpStatus.OK);
    }
}