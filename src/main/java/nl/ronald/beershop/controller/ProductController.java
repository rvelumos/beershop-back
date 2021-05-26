package nl.ronald.beershop.controller;

import nl.ronald.beershop.model.Category;
import nl.ronald.beershop.model.Product;
import nl.ronald.beershop.repository.ProductRepository;
import nl.ronald.beershop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1")
public class ProductController {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        return new MultipartConfigElement("");
    }

    @Bean
    public MultipartResolver multipartResolver() {
        org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        return multipartResolver;
    }

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @GetMapping(value="/products/type/{type}/latest")
    public ResponseEntity<Object> getLatestProducts(@PathVariable("type") long type) {
        List<Product> products = productRepository.findTop3ByTypeOrderByIdDesc(type);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/products/recommended/{category_id}")
    public ResponseEntity<Object> recommendedProducts(@PathVariable("category_id") Integer category_id) {
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
                updateProduct.setCategory(product.getCategory());
                updateProduct.setManufacturer(product.getManufacturer());
                updateProduct.setName(product.getName());
                updateProduct.setPrice(product.getPrice());
                updateProduct.setTaste(product.getTaste());
                updateProduct.setImage(product.getImage());
                updateProduct.setStock(product.getStock());
                updateProduct.setDescription(product.getDescription());
                updateProduct.setType(product.getType());
                return productRepository.save(updateProduct);
            })
            .orElseGet(() -> {
                return productRepository.save(product);
            });
    }

    @PostMapping(value="/admin/product/giftcard")
    public ResponseEntity<Object> createProductGiftCard(@RequestBody Product product) {
        productRepository.save(product);
        URI location;
        return new ResponseEntity<>("Cadeaubon toegevoegd", HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping(value = "/product/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable("id") long id) {
        Optional<Product> product = productRepository.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(value="/admin/product" , method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<Object> createProduct(@ModelAttribute Product product, @RequestParam(value = "documents") MultipartFile multipartFile) throws IOException, NoSuchAlgorithmException {
        productService.addDocument(multipartFile);
        product.setImage(multipartFile.getOriginalFilename());
        productRepository.save(product);
        URI location;
        return new ResponseEntity<>("Toegevoegd", HttpStatus.CREATED);
    }

    @RequestMapping(value="/admin/forms/upload",method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView upload(@RequestParam CommonsMultipartFile file, HttpSession session){
        String path=session.getServletContext().getRealPath("/product_images/");
        String filename=file.getOriginalFilename();

        System.out.println(path+" "+filename);
        try{
            byte barr[]=file.getBytes();

            BufferedOutputStream bout=new BufferedOutputStream(
                    new FileOutputStream(path+"/"+filename));
            bout.write(barr);
            bout.flush();
            bout.close();

        }catch(Exception e){System.out.println(e);}
        return new ModelAndView("upload-success","filename",path+"/"+filename);
    }

    @DeleteMapping("/admin/product/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") long id) {
        productRepository.deleteById(id);
        return new ResponseEntity<>("Product is verwijderd", HttpStatus.OK);
    }
}