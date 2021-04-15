package nl.ronald.beershop.service;

import nl.ronald.beershop.model.Newsletter;

import java.util.List;

public interface NewsletterService {

    public List<Newsletter> getAllNewsletters();
    public Newsletter getNewsletter(long id);
    public void save(Newsletter newsletter);
    public void deleteById(long id);
}
