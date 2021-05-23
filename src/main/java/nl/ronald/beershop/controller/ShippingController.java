package nl.ronald.beershop.controller;

import nl.ronald.beershop.model.Product;
import nl.ronald.beershop.model.Shipping;
import nl.ronald.beershop.repository.ShippingRepository;
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
public class ShippingController {

    @Autowired
    private ShippingRepository shippingRepository;

    @GetMapping(value = "/shippings")
    public ResponseEntity<Object> getShippings() {
        List<Shipping> shippings = shippingRepository.findAll();
        return new ResponseEntity<>(shippings, HttpStatus.OK);
    }

    @GetMapping(value = "/shipping/{id}")
    public ResponseEntity<Object> getShipping(@PathVariable("id") long id) {
        Optional<Shipping> shipping = shippingRepository.findById(id);
        return new ResponseEntity<>(shipping, HttpStatus.OK);
    }

    @PostMapping(value="/shipping")
    public ResponseEntity<Object> createShipping(@RequestBody Shipping shipping) {
        shippingRepository.save(shipping);
        URI location;
        return new ResponseEntity<>("Toegevoegd", HttpStatus.CREATED);
    }

    @PutMapping(value = "/shipping/{id}")
    public Shipping updateShipping(@RequestBody Shipping shipping, @PathVariable Long id) {
        return shippingRepository.findById(id)
            .map(updateShipping -> {
                updateShipping.setOrderId(shipping.getOrderId());
                updateShipping.setAddress(shipping.getAddress());
                updateShipping.setNote(shipping.getNote());
                return shippingRepository.save(updateShipping);
            })
            .orElseGet(() -> {
                return shippingRepository.save(shipping);
            });
    }
}
