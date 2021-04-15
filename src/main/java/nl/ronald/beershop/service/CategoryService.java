package nl.ronald.beershop.service;

import nl.ronald.beershop.model.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> getAllCategories();
    public Category getCategory(long id);
    public void save(Category category);
    public void deleteById(long id);
}
