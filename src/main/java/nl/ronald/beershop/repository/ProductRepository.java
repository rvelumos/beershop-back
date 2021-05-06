package nl.ronald.beershop.repository;

import nl.ronald.beershop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    //List<Product> findAllById(Long id);
    List<Product> findByNameIgnoreCaseContaining(String name);
    List<Product> findAllProductsByType(Long type);
    List<Product> findTop3ByTypeOrderByStockAsc(Long type);
    List<Product> findAllByDiscountNotNull();
    List<Product> findTop5ByCategoryId(Long category_id);
    List<Product> findAllByTypeNotContaining(Long type);


//    @Query( value = "SELECT * from Product p WHERE (:name is null or lower(p.name) LIKE %:name%) " +
//            "AND (p.price = :price or :price = -1) " +
//            "AND (p.category_id IN :categories ) " +
//            "AND (p.taste = :taste OR :taste is null) ",
//            nativeQuery = true
//    )
    @Query( value = "SELECT * from Product p WHERE "+
        " (p.category_id IN :categories OR :categories is null)"+
         "AND p.type = :type",
        nativeQuery = true
    )
    List<Product> findProductByFilterValues(
            //@Param("price") Double price,
//            @Param("taste") String taste,
//            @Param("name") String name,
            @PathVariable("type") long type,
            @Param("categories") List<Long> categories);
}