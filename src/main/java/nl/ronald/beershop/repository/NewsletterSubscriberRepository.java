package nl.ronald.beershop.repository;

import nl.ronald.beershop.model.NewsletterSubscriber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsletterSubscriberRepository extends JpaRepository<NewsletterSubscriber, Long> {

    List<NewsletterSubscriber> findAllById(Long id);
}
