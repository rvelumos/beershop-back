package nl.ronald.beershop.repository;

import nl.ronald.beershop.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.RouteMatcher;

import java.util.List;
import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media, Long> {

    List<Media> findAllById(Long id);
 //   Optional<Media> findByProduct_Id(long product_id);
 //   Optional<Media> findByIdAndProduct_Id(long id, long product_id);
}