package nl.ronald.beershop.controller;

import nl.ronald.beershop.model.Discount;
import nl.ronald.beershop.repository.DiscountRepository;
import nl.ronald.beershop.service.DiscountService;
import nl.ronald.beershop.service.DiscountServiceImpl;
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

    @Autowired
    private DiscountServiceImpl discountServiceImpl;

    @GetMapping(value="/products/giftcards/{code}")
    public ResponseEntity<Object> getGiftcardCode(@PathVariable("code") String code) {
        List<Discount> discounts = discountRepository.findByCode(code);
        return new ResponseEntity<>(discounts, HttpStatus.OK);
    }

    @GetMapping(value="/admin/products/discounts")
    public ResponseEntity<Object> getDiscounts() {
        List<Discount> discounts = discountRepository.findAll();
        return new ResponseEntity<>(discounts, HttpStatus.OK);
    }

    @PostMapping(value="/admin/products/discount")
    public ResponseEntity<Object> createDiscount(@RequestBody Discount discount) {
        discount.setCode(discountServiceImpl.randomCodeGenerator());
        discountRepository.save(discount);
        URI location;
        return new ResponseEntity<>("Toegevoegd", HttpStatus.CREATED);
    }

    @GetMapping(value="/admin/products/discount/{id}")
    public ResponseEntity<Object> getDiscount(@PathVariable("id") long id) {
        Optional<Discount> discounts = discountRepository.findById(id);
        return new ResponseEntity<>(discounts, HttpStatus.OK);
    }

    @GetMapping(value="/admin/products/giftcards/{product_id}")
    public ResponseEntity<Object> getGiftcardUsages(@PathVariable("product_id") long product_id) {
        List<Discount> discounts = discountRepository.findByProductId(product_id);
        return new ResponseEntity<>(discounts, HttpStatus.OK);
    }

    @GetMapping(value="/products/giftcards/customer/{username}")
    public ResponseEntity<Object> getUserGiftCards(@PathVariable("username") String username) {
        List<Discount> giftcards = discountRepository.findByUsernameAndName(username, "Cadeaubon");
        return new ResponseEntity<>(giftcards, HttpStatus.OK);
    }

    @PutMapping(value="/admin/products/discounts/usage/{id}")
    public Discount updateUsages(@RequestBody Discount discount, @PathVariable Long id) {
        return discountRepository.findById(id)
            .map(updateUsages -> {
                updateUsages.setUses(discount.getUses());

                return discountRepository.save(updateUsages);
            })
            .orElseGet(() -> {
                return discountRepository.save(discount);
            });
    }

    @PutMapping(value="/admin/products/discounts/{id}")
    public Discount updateDiscount(@RequestBody Discount discount, @PathVariable Long id) {
        return discountRepository.findById(id)
                .map(updateDiscount -> {
                    updateDiscount.setCode(discount.getCode());
                    updateDiscount.setAllowed_usages(discount.getAllowed_usages());
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
