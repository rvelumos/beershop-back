package nl.ronald.beershop.service;

import nl.ronald.beershop.model.Discount;

import java.util.List;

public interface DiscountService {

    public List<Discount> getAllDiscounts();
    public Discount getDiscount(long id);
    public void save(Discount Discount);
    public void deleteById(long id);

}