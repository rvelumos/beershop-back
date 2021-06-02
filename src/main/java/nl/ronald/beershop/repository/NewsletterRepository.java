package nl.ronald.beershop.repository;

import nl.ronald.beershop.model.Newsletter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsletterRepository extends JpaRepository<Newsletter, Long> {

    List<Newsletter> findAllById(Long id);
}
