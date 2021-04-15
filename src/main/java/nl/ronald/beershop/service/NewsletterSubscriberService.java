package nl.ronald.beershop.service;

import nl.ronald.beershop.model.NewsletterSubscriber;

import java.util.List;

public interface NewsletterSubscriberService {

    public List<NewsletterSubscriber> getAllNewsletterSubscribers();
    public void save(NewsletterSubscriber newsletter);
    public void deleteById(long id);
}
