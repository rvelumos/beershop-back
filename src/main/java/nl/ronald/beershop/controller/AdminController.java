package nl.ronald.beershop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AdminController {

    @GetMapping(path="api/v1/admin/")
    public String getOverview() {
        return "ADMIN PAGE";
    }

    public ResponseEntity<Object> getInfo() {
        return new ResponseEntity<>("InfoRouting endpoint", HttpStatus.OK);
    }

}

