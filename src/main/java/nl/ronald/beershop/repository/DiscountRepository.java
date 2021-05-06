package nl.ronald.beershop.repository;

import nl.ronald.beershop.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

    List<Discount> findAllById(Long id);
    List<Discount> findByCustomerIdAndName(Long customer_id, String name);
    List<Discount> findByProductId(Long product_id);
    List<Discount> findByCode(String code);
}