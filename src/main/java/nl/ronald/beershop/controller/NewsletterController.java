package nl.ronald.beershop.controller;

import nl.ronald.beershop.model.Newsletter;
import nl.ronald.beershop.repository.NewsletterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1/admin")
public class NewsletterController {
    @Autowired
    private NewsletterRepository newsletterRepository;

    @GetMapping(value="/newsletters")
    public ResponseEntity<Object> getNewsletters() {
        List<Newsletter> newsletter = newsletterRepository.findAll();
        return new ResponseEntity<>(newsletter, HttpStatus.OK);
    }

    @PostMapping(value="/newsletter")
    public ResponseEntity<Object> createNewsletter(@RequestBody Newsletter newsletter) {
        newsletterRepository.save(newsletter);
        URI location;
        return new ResponseEntity<>("Nieuwsbrief toegevoegd", HttpStatus.CREATED);
    }

    @GetMapping(value="/newsletter/{id}")
    public ResponseEntity<Object> getNewsletter(@PathVariable("id") long id) {
        Optional<Newsletter> newsletter = newsletterRepository.findById(id);
        return new ResponseEntity<>(newsletter, HttpStatus.OK);
    }

    @DeleteMapping(value="/newsletter/{id}")
    public ResponseEntity<Object> deleteNewsletter(@PathVariable("id") long id) {
        newsletterRepository.deleteById(id);
        return new ResponseEntity<>("Nieuwsbrief verwijderd", HttpStatus.OK);
    }
}