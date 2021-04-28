package nl.ronald.beershop.service;

import nl.ronald.beershop.exception.RecordNotFoundException;
import nl.ronald.beershop.model.Product;
import nl.ronald.beershop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(long id) {
        if (productRepository.existsById(id)) {
            return productRepository.findById(id).get();
        }
        else {
            throw new RecordNotFoundException();
        }
    }

//    public Product getProductCategory(long category_id) {
//        if (productRepository.existsById(category_id)) {
//            return productRepository.findAllByCategory_id(category_id).get((int) category_id);
//        }
//        else {
//            throw new RecordNotFoundException();
//        }
//    }

    @Override
    public void save(Product Product) {
        productRepository.save(Product);
    }

    @Override
    public void deleteById(long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException();
        }
    }

    public List<Product> findProductByFilterValues(long type,
                                                   Optional<String> price,
                                                   Optional<String> category_id,
                                                   Optional<String> taste,
                                                   Optional<String> name
                                                   ) {

//        List<String> categories = new ArrayList<>();
//        if (category_id.isPresent() && !category_id.get().isEmpty()) {
//            for (String g: category_id.get().split(",")) {
//                categories.add(g.toLowerCase().trim());
//            }
//        }
        List<Long> categories = new ArrayList<>();
        if (category_id.isPresent() && !category_id.get().isEmpty()) {
            for (String c : category_id.get().split(",")) {
                categories.add(Long.parseLong(c.trim()));
            }
        }

        Double p = null;
        if (price.isPresent() && !price.get().isEmpty()) {
            p = Double.parseDouble(price.get());
        }

        return productRepository.findProductByFilterValues(
                type,
  //              p,
//                taste.orElse("").toLowerCase().trim(),
//                name.orElse("").toLowerCase().trim(),
                categories);
    }


}

