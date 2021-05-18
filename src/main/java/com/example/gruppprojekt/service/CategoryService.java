package com.example.gruppprojekt.service;

import com.example.gruppprojekt.model.Category;
import com.example.gruppprojekt.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    /**
     * adds category category
     * @param category - Object
     * @return - Object
     */

    public Category addCategory(Category category) {
        category.setCreatedDate(LocalDate.now());
        return categoryRepo.save(category);


    }

    /**
     * update category
     * @param category - Object
     * @return - Object
     */


    public Category updateCategory(Category category, String id) {
        category.setLastModifiedDate(LocalDate.now());


        return categoryRepo.save(category);
    }

    /**
     * get all current categories
     * @return - list of Objects
     */

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    /**
     * get category by id
     * @param id - String
     * @return - Object
     */

    public Category getCategoryById(String id) {
        return categoryRepo.findById(id).get();
    }

    /**
     * delete category by id
     * @param id - String
     * @return - String
     */

    public String deleteCategoryById(String id) {
        categoryRepo.deleteById(id);
        return "Category with id " + id + " was deleted.";
    }

    /**
     * delete category by name
     * @param name - String
     * @return - String
     */

    public String deleteCategoryByName(String name) {
        categoryRepo.deleteByName(name);
        return "Category: " + name + " was deleted.";
    }


    /**
     * Add a list of categories
     * @param categories - list of Objects
     * @return - list of Objects
     */

    public List<Category> addCategories(List<Category> categories) {
        return categoryRepo.saveAll(categories);
    }
}
