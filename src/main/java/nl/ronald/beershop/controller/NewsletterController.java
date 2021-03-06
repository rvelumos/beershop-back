package nl.ronald.beershop.controller;

import nl.ronald.beershop.model.Newsletter;
import nl.ronald.beershop.payload.NewsLetterRequest;
import nl.ronald.beershop.repository.NewsletterRepository;
import nl.ronald.beershop.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1/admin")
public class NewsletterController {
    @Autowired
    private NewsletterRepository newsletterRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping(value="/newsletters")
    public ResponseEntity<Object> getNewsletters() {
        List<Newsletter> newsletter = newsletterRepository.findAll();
        return new ResponseEntity<>(newsletter, HttpStatus.OK);
    }

    @PostMapping(value="/newsletter")
    public ResponseEntity<Object> createNewsletter(@RequestBody Newsletter newsletter) {
        newsletterRepository.save(newsletter);
        return new ResponseEntity<>("Nieuwsbrief toegevoegd", HttpStatus.CREATED);
    }

    @GetMapping(value="/newsletter/{id}")
    public ResponseEntity<Object> getNewsletter(@PathVariable("id") long id) {
        Optional<Newsletter> newsletter = newsletterRepository.findById(id);
        return new ResponseEntity<>(newsletter, HttpStatus.OK);
    }

    @PostMapping(value = "/newsletter/{id}/send_bulk")
    public ResponseEntity<Object> sendContactForm(@RequestBody NewsLetterRequest request, @PathVariable Long id)
    {
        emailService.sendBulkMail(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/newsletter/{id}")
    public Newsletter updateNewsletter(@RequestBody Newsletter newsletter, @PathVariable Long id) {
        return newsletterRepository.findById(id)
            .map(updateNewsletter -> {
                updateNewsletter.setAuthor(newsletter.getAuthor());
                updateNewsletter.setContent(newsletter.getContent());
                updateNewsletter.setName(newsletter.getName());
                return newsletterRepository.save(updateNewsletter);
            })
            .orElseGet(() -> newsletterRepository.save(newsletter));
    }

    @DeleteMapping(value="/newsletter/{id}")
    public ResponseEntity<Object> deleteNewsletter(@PathVariable("id") long id) {
        newsletterRepository.deleteById(id);
        return new ResponseEntity<>("Nieuwsbrief verwijderd", HttpStatus.OK);
    }
}