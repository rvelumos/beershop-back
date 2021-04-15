package nl.ronald.beershop.service;

import nl.ronald.beershop.model.Manufacturer;

import java.util.List;

public interface ManufacturerService {

    public List<Manufacturer> getAllManufacturers();
    public Manufacturer getManufacturer(long id);
    public void save(Manufacturer manufacturer);
    public void deleteById(long id);
}
