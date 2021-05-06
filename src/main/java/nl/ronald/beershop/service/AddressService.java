package nl.ronald.beershop.service;

import nl.ronald.beershop.model.Address;

public interface AddressService {
    public Address getAddress(long id);
    public void save(Address address);
    public void deleteById(long id);
}
