package nl.ronald.beershop.repository;

import nl.ronald.beershop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> findByNameIgnoreCaseContaining(String name);
    List<Product> findTop3ByTypeOrderByIdDesc(Long type);
    List<Product> findAllByDiscountNotNull();
    List<Product> findTop5ByCategoryId(Integer category_id);

    @Query( value = "SELECT * from Product p WHERE "+
        " (p.category_id IN :categories OR :categories is null)"+
         "AND p.type = :type",
        nativeQuery = true
    )
    List<Product> findProductByFilterValues(
            @PathVariable("type") long type,
            @Param("categories") List<Long> categories);
}