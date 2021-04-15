package nl.ronald.beershop.service;

import nl.ronald.beershop.exception.RecordNotFoundException;
import nl.ronald.beershop.model.Newsletter;
import nl.ronald.beershop.repository.NewsletterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsletterServiceImpl implements NewsletterService {

    @Autowired
    private NewsletterRepository newsletterRepository;

    @Override
    public List<Newsletter> getAllNewsletters() {
        return newsletterRepository.findAll();
    }

    @Override
    public Newsletter getNewsletter(long id) {
        if (newsletterRepository.existsById(id)) {
            return newsletterRepository.findById(id).get();
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void save(Newsletter newsletter) {
        newsletterRepository.save(newsletter);
    }

    @Override
    public void deleteById(long id) {
        if (newsletterRepository.existsById(id)) {
            newsletterRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException();
        }
    }


}

