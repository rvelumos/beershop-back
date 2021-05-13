package nl.ronald.beershop.controller;

import nl.ronald.beershop.model.Newsletter;
import nl.ronald.beershop.model.Search;
import nl.ronald.beershop.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1")
public class SearchController {

    @Autowired
    private SearchRepository searchRepository;

    @PostMapping(value="/search/{keyword}")
    public ResponseEntity<Object> createKeyword(@RequestBody Search search, @PathVariable String keyword) {
        search.setAmount(1);
        search.setKeyword(keyword);
        searchRepository.save(search);
        URI location;
        return new ResponseEntity<>("Zoekterm toegevoegd", HttpStatus.CREATED);
    }

//    @PutMapping(value = "/search/{keyword}")
//    public Search updateKeyword(@RequestBody Search search, @PathVariable String keyword) {
//        return searchRepository.findByKeyword(keyword)
//            .map(updateKeyword -> {
//                updateKeyword.setKeyword(search.getKeyword());
//                updateKeyword.setAmount(search.getAmount());
//                return searchRepository.save(updateKeyword);
//            })
//            .orElseGet(() -> {
//                return searchRepository.save(search);
//            });
//    }
}



