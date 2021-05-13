package nl.ronald.beershop.service;

import nl.ronald.beershop.exception.RecordNotFoundException;
import nl.ronald.beershop.model.Search;
import nl.ronald.beershop.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchRepository searchRepository;

    public Search getKeyword(long id) {
        if (searchRepository.existsById(id)) {
            return searchRepository.findById(id).get();
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public Search updateKeyword(String keyword) {
        return null;
    }

    @Override
    public void save(Search search) {
        searchRepository.save(search);
    }

    @Override
    public void deleteById(long id) {
        if (searchRepository.existsById(id)) {
            searchRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException();
        }
    }
}

