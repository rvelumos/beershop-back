package nl.ronald.beershop.repository;

import nl.ronald.beershop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IndexRepository extends JpaRepository<Product, Long> {

    List<Product> findAllById(Long id);
    List<Product> findByType(String name);
    List<Product> findAllProductsByType(Long type);

}