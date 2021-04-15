package nl.ronald.beershop.service;

import nl.ronald.beershop.model.Shipping;

import java.util.List;

public interface ShippingService {

    public List<Shipping> getAllShippings();
    public Shipping getShipping(long id);
    public void save(Shipping shipping);
    public void deleteById(long id);
}
