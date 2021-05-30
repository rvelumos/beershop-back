package nl.ronald.beershop.service;

import nl.ronald.beershop.model.SearchTerms;

public interface SearchTermsService {
    public SearchTerms updateKeyword(String keyword);
    public void save(SearchTerms searchTerms);
    public void deleteById(long id);
}
