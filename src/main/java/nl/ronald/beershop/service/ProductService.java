package nl.ronald.beershop.service;

import nl.ronald.beershop.model.Product;

import java.util.List;

public interface ProductService {

    public List<Product> getAllProducts();
    public Product getProduct(long id);
    public void save(Product Product);
    public void deleteById(long id);

}