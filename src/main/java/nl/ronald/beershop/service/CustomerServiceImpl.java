package nl.ronald.beershop.service;

import nl.ronald.beershop.exception.RecordNotFoundException;
import nl.ronald.beershop.model.Customer;
import nl.ronald.beershop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(long id) {
        if (customerRepository.existsById(id)) {
            return customerRepository.findById(id).get();
        }
        else {
            throw new RecordNotFoundException();
        }
    }

//    @Override
//    public List<Customer> getCustomersTitleStartsWith(String title) {
//        return customerRepository.findAllByTitleStartingWith(title);
//    }

    @Override
    public void save(Customer Customer) {
        customerRepository.save(Customer);
    }

    @Override
    public void deleteById(long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException();
        }
    }


}

