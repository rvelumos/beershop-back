package nl.ronald.beershop.controller;

import nl.ronald.beershop.model.Address;
import nl.ronald.beershop.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @RequestMapping(value="/admin/address/{id}")
    public ResponseEntity<Object> getAddress(@PathVariable("id") long id) {
        Optional<Address> address = addressRepository.findById(id);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @RequestMapping(value="/address/customer/{username}")
    public ResponseEntity<Object> getCustomerAddress(@PathVariable("username") String username) {
        List<Address> address = addressRepository.findByUsername(username);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @PostMapping(value="/address")
    public ResponseEntity<Object> createAddress(@RequestBody Address address) {
        addressRepository.save(address);
        return new ResponseEntity<>("Adres toegevoegd", HttpStatus.CREATED);
    }

    @PutMapping(value = "/address/{id}")
    public Address updateAddress(@RequestBody Address address, @PathVariable Long id) {
        return addressRepository.findById(id)
            .map(updateAddress -> {
                updateAddress.setStreet(address.getStreet());
                updateAddress.setStreetAdd(address.getStreetAdd());
                updateAddress.setNumber(address.getNumber());
                updateAddress.setAddressType(address.getAddressType());
                updateAddress.setPostalCode(address.getPostalCode());
                updateAddress.setProvince(address.getProvince());
                updateAddress.setCountry(address.getCountry());
                updateAddress.setCity(address.getCity());
                return addressRepository.save(updateAddress);
            })
            .orElseGet(() -> addressRepository.save(address));
    }

    @DeleteMapping("/admin/address/{id}")
    public ResponseEntity<Object> deleteAddress(@PathVariable("id") long id) {
        addressRepository.deleteById(id);
        return new ResponseEntity<>("Adresgegevens verwijderd", HttpStatus.OK);
    }
}
