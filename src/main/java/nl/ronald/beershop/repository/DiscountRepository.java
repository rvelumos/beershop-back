package nl.ronald.beershop.repository;

import nl.ronald.beershop.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

    List<Discount> findAllById(Long id);
    List<Discount> findByUsernameAndName(String username, String name);
    List<Discount> findByUsername(String Username);
    List<Discount> findByProductId(Long product_id);
    List<Discount> findByCode(String code);
}