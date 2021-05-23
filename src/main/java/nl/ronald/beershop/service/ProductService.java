package nl.ronald.beershop.service;

import nl.ronald.beershop.model.Product;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    public List<Product> getAllProducts();
    public Product getProduct(long id);
    public void save(Product Product);
    public void deleteById(long id);
    public void addDocument(MultipartFile multipartFiles) throws NoSuchAlgorithmException, IOException;
    List <Product>findProductByFilterValues(
            long type,
            Optional<String> price,
            Optional<String> category_id,
            Optional<String> taste
            );
}