package nl.ronald.beershop.controller;

import nl.ronald.beershop.model.Product;
import nl.ronald.beershop.model.Search;
import nl.ronald.beershop.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1")
public class SearchController {

    @Autowired
    private SearchRepository searchRepository;

    @GetMapping(value = "/keywords/top")
    public ResponseEntity<Object> getTopKeywords() {
        List<Search> search = searchRepository.findTop10Keywords();
        return new ResponseEntity<>(search, HttpStatus.OK);
    }

    @PostMapping(value="/search/{keyword}")
    public ResponseEntity<Object> createKeyword(@RequestBody Search search, @PathVariable String keyword) {
        search.setAmount(1);
        search.setKeyword(keyword);
        searchRepository.save(search);
        return new ResponseEntity<>("Zoekterm toegevoegd", HttpStatus.CREATED);
    }

}



