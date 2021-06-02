package nl.ronald.beershop.service;

import nl.ronald.beershop.exception.RecordNotFoundException;
import nl.ronald.beershop.model.Address;
import nl.ronald.beershop.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address getAddress(long id) {
        if (addressRepository.existsById(id)) {
            return addressRepository.findById(id).get();
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void save(Address address) {
        addressRepository.save(address);
    }

    @Override
    public void deleteById(long id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException();
        }
    }
}

