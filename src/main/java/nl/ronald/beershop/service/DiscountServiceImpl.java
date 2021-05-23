package nl.ronald.beershop.service;

import nl.ronald.beershop.exception.RecordNotFoundException;
import nl.ronald.beershop.model.Discount;
import nl.ronald.beershop.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Override
    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    @Override
    public Discount getDiscount(long id) {
        if (discountRepository.existsById(id)) {
            return discountRepository.findById(id).get();
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void save(Discount discount) {
        discountRepository.save(discount);
    }

    @Override
    public void deleteById(long id) {
        if (discountRepository.existsById(id)) {
            discountRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    public String randomCodeGenerator() {
        String type = "GC";

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 18;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        buffer.append(type);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            if(i % 5 == 0){
                buffer.append("-");
            }

            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString().toUpperCase();

        return(generatedString);
    }


}

