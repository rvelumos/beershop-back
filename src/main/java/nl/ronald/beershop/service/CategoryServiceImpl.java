package nl.ronald.beershop.service;

import nl.ronald.beershop.exception.RecordNotFoundException;
import nl.ronald.beershop.model.Category;
import nl.ronald.beershop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(long id) {
        if (categoryRepository.existsById(id)) {
            return categoryRepository.findById(id).get();
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteById(long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException();
        }
    }


}

