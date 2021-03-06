package nl.ronald.beershop.repository;

import nl.ronald.beershop.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllById(Long id);
    List<Customer> findByUsername(String username);
}