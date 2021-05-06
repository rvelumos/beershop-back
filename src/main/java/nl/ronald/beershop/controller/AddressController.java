package nl.ronald.beershop.controller;

import nl.ronald.beershop.model.Address;
import nl.ronald.beershop.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1")
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;

    @GetMapping(value="/admin/address/{id}")
    public ResponseEntity<Object> getAddress(@PathVariable("id") long id) {
        Optional<Address> address = addressRepository.findById(id);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @PostMapping(value="/admin/address")
    public ResponseEntity<Object> createAddress(@RequestBody Address address) {
        addressRepository.save(address);
        URI location;
        return new ResponseEntity<>("Adres toegevoegd", HttpStatus.CREATED);
    }

    @PutMapping(value = "/admin/address/{id}")
    public Address updateAddress(@RequestBody Address address, @PathVariable Long id) {
        return addressRepository.findById(id)
                .map(updateAddress -> {
                    updateAddress.setCustomer(address.getCustomer());
                    return addressRepository.save(updateAddress);
                })
                .orElseGet(() -> {
                    return addressRepository.save(address);
                });
    }

    @DeleteMapping("/admin/address/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable("id") long id) {
        addressRepository.deleteById(id);
        return new ResponseEntity<>("Adresgegevens verwijderd", HttpStatus.OK);
    }
}
