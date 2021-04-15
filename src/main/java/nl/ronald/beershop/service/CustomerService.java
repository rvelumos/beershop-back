package nl.ronald.beershop.service;

import nl.ronald.beershop.model.Customer;
import nl.ronald.beershop.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CustomerService {

    public List<Customer> getAllCustomers();
    public Customer getCustomer(long id);
    //public List<Customer> getCustomersByEmail(String email);
    public void save(Customer customer);
    public void deleteById(long id);

}
