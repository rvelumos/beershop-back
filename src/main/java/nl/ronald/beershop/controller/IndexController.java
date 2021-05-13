package nl.ronald.beershop.controller;

import nl.ronald.beershop.model.Customer;
import nl.ronald.beershop.model.Index;
import nl.ronald.beershop.model.Product;
import nl.ronald.beershop.payload.SimpleEmailRequest;
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
    public ResponseEntity<Object> sendContactForm(@RequestBody SimpleEmailRequest request)
    {
        emailService.sendContactForm(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
