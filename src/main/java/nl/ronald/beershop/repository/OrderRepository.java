package nl.ronald.beershop.repository;

import nl.ronald.beershop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllById(Long id);
    List<Order> findByUsername(String username);

}