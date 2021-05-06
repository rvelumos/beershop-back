package nl.ronald.beershop.controller;

import nl.ronald.beershop.model.Product;
import nl.ronald.beershop.repository.ProductRepository;
import nl.ronald.beershop.service.ProductService;
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

    @Autowired
    ProductService productService;

    @GetMapping(value="/products/type/{type}/latest")
    public ResponseEntity<Object> getLatestProducts(@PathVariable("type") long type) {
        List<Product> products = productRepository.findTop3ByTypeOrderByStockAsc(type);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/products/recommended/{category_id}")
    public ResponseEntity<Object> recommendedProducts(@PathVariable("category_id") long category_id) {
        List<Product> products = productRepository.findTop5ByCategoryId(category_id);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/products")
    public ResponseEntity<Object> getProducts() {
        List<Product> products = productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/products/discount")
    public ResponseEntity<Object> getDiscountProducts(Product product) {
        List<Product> products = productRepository.findAllByDiscountNotNull();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/products/search/{search}")
    public ResponseEntity<Object> findProducts(@PathVariable("search") String search) {
        List<Product> products = productRepository.findByNameIgnoreCaseContaining(search);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(value="/products/type/{type}")
    @ResponseBody
    public ResponseEntity<Object> filterProducts(
            @PathVariable("type") long type,
            @RequestParam (name="price", defaultValue = "") Optional<String> price,
            @RequestParam (name="category_id", defaultValue = "") Optional<String> category_id,
            @RequestParam (name="taste", defaultValue = "")Optional<String> taste) {

            List<Product> products = productService.findProductByFilterValues(type, price, category_id, taste);

            return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping(value = "/admin/product/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable Long id) {
        return productRepository.findById(id)
            .map(updateProduct -> {
                updateProduct.setCategoryId(product.getCategoryId());
                updateProduct.setManufacturer(product.getManufacturer());
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

    @CrossOrigin
    @GetMapping(value = "/product/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable("id") long id) {
        Optional<Product> product = productRepository.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping(value="/admin/product")
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        productRepository.save(product);
        URI location;
        return new ResponseEntity<>("Toegevoegd", HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/product/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") long id) {
        productRepository.deleteById(id);
        return new ResponseEntity<>("Product is verwijderd", HttpStatus.OK);
    }
}