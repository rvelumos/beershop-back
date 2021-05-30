package nl.ronald.beershop.service;

import nl.ronald.beershop.exception.RecordNotFoundException;
import nl.ronald.beershop.model.SearchTerms;
import nl.ronald.beershop.repository.SearchTermsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchTermsServiceImpl implements SearchTermsService {

    @Autowired
    private SearchTermsRepository searchTermsRepository;

    public SearchTerms getKeyword(long id) {
        if (searchTermsRepository.existsById(id)) {
            return searchTermsRepository.findById(id).get();
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public SearchTerms updateKeyword(String keyword) {
        return null;
    }

    @Override
    public void save(SearchTerms searchTerms) {
        searchTermsRepository.save(searchTerms);
    }

    @Override
    public void deleteById(long id) {
        if (searchTermsRepository.existsById(id)) {
            searchTermsRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException();
        }
    }
}

