package nl.ronald.beershop.repository;

import nl.ronald.beershop.model.Search;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchRepository extends JpaRepository<Search, Long> {

    List<Search> findByKeyword(String keyword);
}