package nl.ronald.beershop.service;

import nl.ronald.beershop.exception.RecordNotFoundException;
import nl.ronald.beershop.model.NewsletterSubscriber;
import nl.ronald.beershop.repository.NewsletterSubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsletterSubscriberServiceImpl implements NewsletterSubscriberService {

    @Autowired
    private NewsletterSubscriberRepository newsletterSubscriberRepository;

    @Override
    public List<NewsletterSubscriber> getAllNewsletterSubscribers() {
        return newsletterSubscriberRepository.findAll();
    }

    @Override
    public void save(NewsletterSubscriber NewsletterSubscriber) {
        newsletterSubscriberRepository.save(NewsletterSubscriber);
    }

    @Override
    public void deleteById(long id) {
        if (newsletterSubscriberRepository.existsById(id)) {
            newsletterSubscriberRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException();
        }
    }


}

