package nl.ronald.beershop.service;

import nl.ronald.beershop.exception.RecordNotFoundException;
import nl.ronald.beershop.model.Product;
import nl.ronald.beershop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


}

