package nl.ronald.beershop.service;

import nl.ronald.beershop.exception.RecordNotFoundException;
import nl.ronald.beershop.model.Shipping;
import nl.ronald.beershop.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingServiceImpl implements ShippingService {

    @Autowired
    private ShippingRepository shippingRepository;

    @Override
    public List<Shipping> getAllShippings() {
        return shippingRepository.findAll();
    }

    @Override
    public Shipping getShipping(long id) {
        if (shippingRepository.existsById(id)) {
            return shippingRepository.findById(id).get();
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void save(Shipping shipping) {
        shippingRepository.save(shipping);
    }

    @Override
    public void deleteById(long id) {
        if (shippingRepository.existsById(id)) {
            shippingRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException();
        }
    }


}

