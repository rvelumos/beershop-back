package nl.ronald.beershop.repository;

import nl.ronald.beershop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //List<Product> findAllById(Long id);
    List<Product> findByNameIgnoreCaseContaining(String name);
    List<Product> findAllProductsByType(Long type);
    List<Product> findTop5ByTypeOrderByStockAsc(Long type);
    List<Product> findAllByDiscountNotNull();
    List<Product> findTop5ByCategoryId(Long category_id);

    @Query( "SELECT p from Product p WHERE (:name is null or p.name LIKE %:name%) " +
            "AND (p.price = :price or :price is null) " +
            "AND (p.categoryId = :category_id OR :category_id is null) " +
            "AND (p.manufacturer_id = :manufacturer_id OR :manufacturer_id is null) " +
            "AND (p.taste = :taste OR :taste is null) "
    )
    List<Product> findProductByFilterValues(Optional<Long> price, Optional<Long> category_id, Optional<String> taste, Optional<String> name, Optional<Long> manufacturer_id);
}