package nl.ronald.beershop.repository;

import nl.ronald.beershop.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentRepository extends JpaRepository<Document, Long> {

}