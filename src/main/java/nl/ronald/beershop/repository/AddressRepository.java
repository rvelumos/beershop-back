package nl.ronald.beershop.repository;

import nl.ronald.beershop.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllById(Long id);
    List<Address> findByUsername(String username);

}