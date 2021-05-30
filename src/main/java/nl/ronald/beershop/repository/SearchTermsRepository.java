package nl.ronald.beershop.repository;

import nl.ronald.beershop.model.SearchTerms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SearchTermsRepository extends JpaRepository<SearchTerms, Long> {

    List<SearchTerms> findByKeyword(String keyword);
    @Query( value = "SELECT st.keyword, sum(st.amount) AS keyword_amount\n " +
            "FROM  search_terms st  -- ?\n " +
            "GROUP  BY st.keyword\n " +
            "ORDER  BY keyword_amount DESC NULLS LAST\n " +
            "LIMIT  10;",
            nativeQuery = true
    )
    List<SearchTerms> findTop10Keywords();
}