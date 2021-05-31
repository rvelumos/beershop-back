package nl.ronald.beershop.repository;

import nl.ronald.beershop.model.ISearchTermsCount;
import nl.ronald.beershop.model.SearchTerms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SearchTermsRepository extends JpaRepository<SearchTerms, Long> {

    List<SearchTerms> findByKeyword(String keyword);
    @Query( value = "SELECT st.keyword as keywordName, sum(st.amount) AS keywordAmount\n " +
            "FROM  search_terms st " +
            "GROUP  BY st.keyword, st.amount\n " +
            "ORDER  BY keywordAmount DESC LIMIT 10 \n ",
            nativeQuery = true
    )
    List<ISearchTermsCount> countKeywordNameByKeywordAmountInterface();
}