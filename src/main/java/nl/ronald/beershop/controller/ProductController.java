package nl.ronald.beershop.controller;

import lombok.Data;
import nl.ronald.beershop.model.Product;
import nl.ronald.beershop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(value="/products/type/{type}/latest/")
    public ResponseEntity<Object> getLatestProducts(@PathVariable("type") long type) {
        List<Product> products = productRepository.findTop5ByTypeOrderByStockAsc(type);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/recommended/{category_id}/")
    public ResponseEntity<Object> recommendedProducts(@PathVariable("category_id") long category_id) {
        List<Product> products = productRepository.findTop5ByCategoryId(category_id);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    //everything
    @GetMapping(value = "/products/all/")
    public ResponseEntity<Object> getProducts() {
        List<Product> products = productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // check bonus products
    @GetMapping(value = "/products/type/{type}/")
    public ResponseEntity<Object> getProducts(@PathVariable("type") long type) {
        List<Product> products = productRepository.findAllProductsByType(type);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/products/discount/")
    public ResponseEntity<Object> getDiscountProducts(Product product) {
        List<Product> products = productRepository.findAllByDiscountNotNull();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/products/search/{search}")
    public ResponseEntity<Object> findProducts(@PathVariable("search") String search) {
        List<Product> products = productRepository.findByNameIgnoreCaseContaining(search);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(value="/products/")
    @ResponseBody
    public ResponseEntity<Object> filterProducts(
            @RequestParam Optional<Long> price,
            @RequestParam Optional<Long> category_id,
            @RequestParam Optional<String> taste,
            @RequestParam Optional<String> name,
            @RequestParam Optional<Long> manufacturer_id) {

            List<Product> products = productRepository.findProductByFilterValues(price, category_id, taste, name, manufacturer_id);

            return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // auth check & related authorities fix
    @PutMapping(value = "/admin/product/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable Long id) {
        return productRepository.findById(id)
            .map(updateProduct -> {
                updateProduct.setCategoryId(product.getCategoryId());
                updateProduct.setManufacturer_id(product.getManufacturer_id());
                updateProduct.setName(product.getName());
                updateProduct.setPrice(product.getPrice());
                updateProduct.setTaste(product.getTaste());
                updateProduct.setStock(product.getStock());
                updateProduct.setDescription(product.getDescription());
                updateProduct.setType(product.getType());
                return productRepository.save(updateProduct);
            })
            .orElseGet(() -> {
                return productRepository.save(product);
            });
    }

    // auth check
    public Product updateProductStock(@RequestBody Product product, @PathVariable Long id) {
        return productRepository.findById(id)
            .map(updateProduct -> {
                updateProduct.setStock(product.getStock());
                return productRepository.save(updateProduct);
            })
            .orElseGet(() -> {
                return productRepository.save(product);
            });
    }

    //auth & order join fix
    @CrossOrigin
    @GetMapping(value = "/product/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable("id") long id) {
        Optional<Product> product = productRepository.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    //auth check
    @PostMapping(value="/product")
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        productRepository.save(product);
        URI location;
        return new ResponseEntity<>("Toegevoegd", HttpStatus.CREATED);
    }

    //auth check
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") long id) {
        productRepository.deleteById(id);
        return new ResponseEntity<>("Product is verwijderd", HttpStatus.OK);
    }
}