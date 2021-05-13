package nl.ronald.beershop.service;

import nl.ronald.beershop.model.Search;

import java.util.List;

public interface SearchService {
    public Search updateKeyword(String keyword);
    public void save(Search search);
    public void deleteById(long id);
}
