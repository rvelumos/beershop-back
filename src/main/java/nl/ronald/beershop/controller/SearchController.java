package nl.ronald.beershop.controller;

import nl.ronald.beershop.model.ISearchTermsCount;
import nl.ronald.beershop.model.SearchTerms;
import nl.ronald.beershop.repository.SearchTermsRepository;
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
    private SearchTermsRepository searchTermsRepository;

    @GetMapping(value = "/keywords/top")
    public ResponseEntity<Object> getTopKeywords() {
        List<ISearchTermsCount> searchTerms = searchTermsRepository.countKeywordNameByKeywordAmountInterface();
        return new ResponseEntity<>(searchTerms, HttpStatus.OK);
    }

    @PostMapping(value="/search/{keyword}")
    public ResponseEntity<Object> createKeyword(@RequestBody SearchTerms searchTerms, @PathVariable String keyword) {
        searchTerms.setAmount(1);
        searchTerms.setKeyword(keyword);
        searchTermsRepository.save(searchTerms);
        return new ResponseEntity<>("Zoekterm toegevoegd", HttpStatus.CREATED);
    }

}



