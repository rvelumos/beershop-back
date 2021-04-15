package nl.ronald.beershop.service;

import nl.ronald.beershop.exception.RecordNotFoundException;
import nl.ronald.beershop.model.Manufacturer;
import nl.ronald.beershop.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Override
    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer getManufacturer(long id) {
        if (manufacturerRepository.existsById(id)) {
            return manufacturerRepository.findById(id).get();
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void save(Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
    }

    @Override
    public void deleteById(long id) {
        if (manufacturerRepository.existsById(id)) {
            manufacturerRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException();
        }
    }


}

