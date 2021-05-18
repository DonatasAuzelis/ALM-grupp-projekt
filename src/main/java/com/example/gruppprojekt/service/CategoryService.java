package com.example.gruppprojekt.service;

import com.example.gruppprojekt.model.Category;
import com.example.gruppprojekt.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    public Category addCategory(Category category) {
       return categoryRepo.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Category getCategoryById(String id) {
        return categoryRepo.findById(id).get();
    }

    public String deleteCategoryById(String id) {
        categoryRepo.deleteById(id);
        return "Category with id " + id + " was deleted.";
    }

    public String deleteCategoryByName(String name) {
        categoryRepo.deleteByName(name);
        return "Category: " + name + " was deleted.";
    }

    public Category updateCategory(String id, Category updatedCategory) throws ClassNotFoundException {
       Optional<Category> newCategory = categoryRepo.findById(id);

       if (newCategory.isPresent()) {
           newCategory.get().setName(updatedCategory.getName());
           return categoryRepo.save(newCategory.get());
       } else {
           throw new ClassNotFoundException();
        }
    }

    public List<Category> addUsers(List<Category> categories) {
        return categoryRepo.saveAll(categories);
    }
}
