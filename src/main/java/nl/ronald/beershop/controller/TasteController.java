package nl.ronald.beershop.controller;

import nl.ronald.beershop.model.Taste;
import nl.ronald.beershop.repository.TasteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1")
public class TasteController {
    @Autowired
    private TasteRepository tasteRepository;

    @RequestMapping( {"/products/tastes", "/admin/products/tastes"} )
    public ResponseEntity<Object> getTastes() {
        List<Taste> taste = tasteRepository.findAll();
        return new ResponseEntity<>(taste, HttpStatus.OK);
    }

    @GetMapping(value="/products/taste/{id}")
    public ResponseEntity<Object> getTaste(@PathVariable("id") long id) {
        Optional<Taste> taste = tasteRepository.findById(id);
        return new ResponseEntity<>(taste, HttpStatus.OK);
    }

    @PostMapping(value="/admin/products/taste")
    public ResponseEntity<Object> createTaste(@RequestBody Taste taste) {
        tasteRepository.save(taste);
        URI location;
        return new ResponseEntity<>("Categorie toegevoegd", HttpStatus.CREATED);
    }

    @PutMapping(value = "/admin/products/taste/{id}")
    public Taste updateTaste(@RequestBody Taste taste, @PathVariable Long id) {
        return tasteRepository.findById(id)
            .map(updateTaste -> {
                updateTaste.setName(taste.getName());
                return tasteRepository.save(updateTaste);
            })
            .orElseGet(() -> {
                return tasteRepository.save(taste);
            });
    }

    @DeleteMapping("/admin/products/taste/{id}")
    public ResponseEntity<Object> deleteTaste(@PathVariable("id") long id) {
        tasteRepository.deleteById(id);
        return new ResponseEntity<>("Categorie verwijderd", HttpStatus.OK);
    }
}