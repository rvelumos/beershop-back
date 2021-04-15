package nl.ronald.beershop.repository;

import nl.ronald.beershop.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllById(Long id);

}