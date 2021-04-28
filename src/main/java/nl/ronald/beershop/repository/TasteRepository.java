package nl.ronald.beershop.repository;

import nl.ronald.beershop.model.Taste;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TasteRepository extends JpaRepository<Taste, Long> {

    List<Taste> findAllById(Long id);

}