package nl.ronald.beershop.repository;

import nl.ronald.beershop.model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {

    List<Shipping> findAllById(Long id);

}