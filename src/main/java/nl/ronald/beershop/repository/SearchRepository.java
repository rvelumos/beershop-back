package nl.ronald.beershop.repository;

import nl.ronald.beershop.model.Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SearchRepository extends JpaRepository<Search, Long> {

    List<Search> findByKeyword(String keyword);
    @Query( value = "select distinct keyword, sum (amount) as keyword_amount from search " +
            "group by keyword order by keyword_amount DESC",
            nativeQuery = true
    )
    List<Search> findTop10Keywords();
}