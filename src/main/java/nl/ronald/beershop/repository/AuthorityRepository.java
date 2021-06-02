package nl.ronald.beershop.repository;

import nl.ronald.beershop.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, String> {

}