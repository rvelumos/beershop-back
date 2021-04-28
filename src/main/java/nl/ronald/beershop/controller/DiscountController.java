package nl.ronald.beershop.controller;

import nl.ronald.beershop.model.Discount;
import nl.ronald.beershop.repository.DiscountRepository;
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
public class DiscountController {
    @Autowired
    private DiscountRepository discountRepository;

    @GetMapping(value="/admin/products/discounts")
    public ResponseEntity<Object> getDiscounts() {
        List<Discount> discounts = discountRepository.findAll();
        return new ResponseEntity<>(discounts, HttpStatus.OK);
    }

    @PostMapping(value="/admin/products/discounts")
    public ResponseEntity<Object> createDiscount(@RequestBody Discount discount) {
        String discount_type=discount.getDiscount_type().toString();
        discount.setCode(discount.randomCodeGenerator(discount_type));
        discountRepository.save(discount);
        URI location;
        return new ResponseEntity<>("Toegevoegd", HttpStatus.CREATED);
    }

    @GetMapping(value="/admin/products/discounts/{id}")
    public ResponseEntity<Object> getDiscount(@PathVariable("id") long id) {
        Optional<Discount> discounts = discountRepository.findById(id);
        return new ResponseEntity<>(discounts, HttpStatus.OK);
    }

    @GetMapping(value="/admin/giftcards/{product_id}")
    public ResponseEntity<Object> getGiftcardUsages(@PathVariable("product_id") long product_id) {
        List<Discount> discounts = discountRepository.findByProductId(product_id);
        return new ResponseEntity<>(discounts, HttpStatus.OK);
    }

    @GetMapping(value="/giftcards/customer/{customer_id}")
    public ResponseEntity<Object> getUserGiftCards(@PathVariable("customer_id") long customer_id) {
        List<Discount> giftcards = discountRepository.findByCustomerIdAndName(customer_id, "Cadeaubon");
        return new ResponseEntity<>(giftcards, HttpStatus.OK);
    }

    @PutMapping(value="/admin/products/discounts/{id}")
    public Discount updateDiscount(@RequestBody Discount discount, @PathVariable Long id) {
        return discountRepository.findById(id)
                .map(updateDiscount -> {
                    updateDiscount.setCode(discount.getCode());
                    updateDiscount.setAllowed_usages(discount.getAllowed_usages());
                    updateDiscount.setDiscount_type(discount.getDiscount_type());
                    updateDiscount.setExpiration_date(discount.getExpiration_date());
                    updateDiscount.setAmount(discount.getAmount());
                    updateDiscount.setName(discount.getName());

                    return discountRepository.save(updateDiscount);
                })
                .orElseGet(() -> {
                    return discountRepository.save(discount);
                });
    }

    //auth fix!
    @DeleteMapping(value="/admin/products/discounts/{id}")
    public ResponseEntity<Object> deleteDiscount(@PathVariable("id") long id) {
        discountRepository.deleteById(id);
        return new ResponseEntity<>("Verwijderd", HttpStatus.OK);
    }


}
