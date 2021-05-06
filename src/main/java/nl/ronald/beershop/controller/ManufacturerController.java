package nl.ronald.beershop.controller;

import nl.ronald.beershop.model.Manufacturer;
import nl.ronald.beershop.repository.ManufacturerRepository;
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
public class ManufacturerController {
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @GetMapping("/manufacturers")
    public ResponseEntity<Object> getManufacturers() {
        List<Manufacturer> manufacturer = manufacturerRepository.findAll();
        return new ResponseEntity<>(manufacturer, HttpStatus.OK);
    }

    @PostMapping(value="/manufacturer")
    public ResponseEntity<Object> createManufacturer(@RequestBody Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
        URI location;
        return new ResponseEntity<>("Fabrikant toegevoegd", HttpStatus.CREATED);
    }

    @GetMapping(value="/manufacturer/{id}")
    public ResponseEntity<Object> getManufacturer(@PathVariable("id") long id) {
        Optional<Manufacturer> manufacturer = manufacturerRepository.findById(id);
        return new ResponseEntity<>(manufacturer, HttpStatus.OK);
    }

    @PutMapping(value = "/admin/manufacturer/{id}")
    public Manufacturer updateManufacturer(@RequestBody Manufacturer manufacturer, @PathVariable Long id) {
        return manufacturerRepository.findById(id)
            .map(updateManufacturer -> {
                updateManufacturer.setName(manufacturer.getName());
                updateManufacturer.setEmail(manufacturer.getEmail());
                updateManufacturer.setPhone(manufacturer.getPhone());
                updateManufacturer.setUser_id(manufacturer.getUser_id());
                return manufacturerRepository.save(updateManufacturer);
            })
            .orElseGet(() -> {
                return manufacturerRepository.save(manufacturer);
            });
    }

    @DeleteMapping("/admin/manufacturer/{id}")
    public ResponseEntity<Object> deleteManufacturer(@PathVariable("id") long id) {
        manufacturerRepository.deleteById(id);
        return new ResponseEntity<>("Fabrikant verwijderd", HttpStatus.OK);
    }
}