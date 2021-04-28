package nl.ronald.beershop.controller;

import nl.ronald.beershop.model.Customer;
import nl.ronald.beershop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(value = {"/customer", "/admin/customer"}, method = POST)
    public ResponseEntity<Object> createCustomer(@RequestBody Customer customer) {
        customerRepository.save(customer);
        URI location;
        return new ResponseEntity<>("Klant is toegevoegd", HttpStatus.CREATED);
    }

    @RequestMapping(value={"/admin/customer/{id}", "customer/{id}"})
    public ResponseEntity<Object> getCustomer(@PathVariable("id") long id) {
        Optional<Customer> customers = customerRepository.findById(id);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @DeleteMapping("/admin/customer/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable("id") long id) {
        customerRepository.deleteById(id);
        return new ResponseEntity<>("Klant is verwijderd", HttpStatus.OK);
    }

    @GetMapping("/admin/customers")
    public ResponseEntity<Object> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    // auth check & related authorities fix
    @PutMapping(value = "/admin/customer/{id}")
    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable Long id) {

        return customerRepository.findById(id)
            .map(updateCustomer -> {
                updateCustomer.setFirstname(customer.getFirstname());
                updateCustomer.setLastname(customer.getLastname());
                updateCustomer.setAddress(customer.getAddress());
                updateCustomer.setEmail(customer.getEmail());
                updateCustomer.setBirth_date(customer.getBirth_date());
                updateCustomer.setPhone(customer.getPhone());
                updateCustomer.setCustomer_points(customer.getCustomer_points());
                updateCustomer.setNewsletter(customer.isNewsletter());
                updateCustomer.setSex(customer.getSex());

                return customerRepository.save(updateCustomer);
            })
            .orElseGet(() -> {
                return customerRepository.save(customer);
            });
    }
}
