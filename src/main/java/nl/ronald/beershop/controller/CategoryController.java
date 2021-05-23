package nl.ronald.beershop.controller;

import nl.ronald.beershop.model.Category;
import nl.ronald.beershop.repository.CategoryRepository;
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
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping( {"/products/categories", "/admin/products/categories"} )
    public ResponseEntity<Object> getCategories() {
        List<Category> category = categoryRepository.findAll();
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping(value="/products/category/{id}")
    public ResponseEntity<Object> getCategory(@PathVariable("id") long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping(value="/admin/products/category")
    public ResponseEntity<Object> createCategory(@RequestBody Category category) {
        categoryRepository.save(category);
        URI location;
        return new ResponseEntity<>("Categorie toegevoegd", HttpStatus.CREATED);
    }

    @PutMapping(value = "/admin/products/category/{id}")
    public Category updateCategory(@RequestBody Category category, @PathVariable Long id) {
        return categoryRepository.findById(id)
            .map(updateCategory -> {
                updateCategory.setName(category.getName());
                return categoryRepository.save(updateCategory);
            })
            .orElseGet(() -> {
                return categoryRepository.save(category);
            });
    }

    @DeleteMapping("/admin/products/category/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable("id") long id) {
        categoryRepository.deleteById(id);
        return new ResponseEntity<>("Categorie verwijderd", HttpStatus.OK);
    }
}