package com.example.gruppprojekt.service;

import com.example.gruppprojekt.model.Category;
import com.example.gruppprojekt.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;


    /**
     * Adds new category if category with the same name doesnt already exist
     * @param newCategory - Object
     * @return - response message
     */
    public Category addCategory(Category newCategory) {
        Category existingCategory = categoryRepo.findCategoryByName(newCategory.getName()).orElse(null);

        if (existingCategory != null) {
            throw new IllegalArgumentException("Category already exists");
        } else {
            newCategory.setBooks(new ArrayList<>());
            return categoryRepo.save(newCategory);
        }



    }

    /**
     * updates category if the id exists already and the name doesn't already exist
     *
     * @param updatedCategory - Object
     * @param id - String
     * @return - response message
     */


    public Category updateCategory(Category updatedCategory, String id) {
        //System.out.println(id); kontrolleras senare
        //System.out.println(id.getClass());
        Category existingCategoryWithSameId = categoryRepo.findById(updatedCategory.getId()).orElse(null); //funkar inte via pathvariable
        Category existingCategoryWithSameName = categoryRepo.findCategoryByName(updatedCategory.getName()).orElse(null);


        if (existingCategoryWithSameId != null && existingCategoryWithSameName == null) {
            updatedCategory.setId(existingCategoryWithSameId.getId());
            updatedCategory.setBooks(existingCategoryWithSameId.getBooks());
            updatedCategory.setLastModifiedDate(LocalDate.now());
            updatedCategory.setCreatedDate(existingCategoryWithSameId.getCreatedDate());
            return categoryRepo.save(updatedCategory);
        } else {
            throw new IllegalArgumentException("No category with that id or category name is already in use, try again!");
        }

    }

    /**
     * get all categories
     * @return - response message
     */
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }


    /**
     * finds category based on id if it exists
     * @param id - String
     * @return - response message
     */
    public Category getCategoryById(String id) {
        Category existingCategory = categoryRepo.findById(id).orElse(null);

        if (existingCategory != null) {
            return categoryRepo.findById(id).get();
        } else {
            throw new IllegalArgumentException("No category with that id");
        }
    }


    /**
     * delete category based on id if id exists
     * @param id - String
     * @return - response message
     */
    public String deleteCategoryById(String id) {
        Category existingCategory = categoryRepo.findById(id).orElse(null);

        if (existingCategory != null) {
            categoryRepo.deleteById(id);
            return "Category with id " + id + " was deleted.";
        } else {
            throw new IllegalArgumentException("No category with that id");
        }

    }

    /**
     * delete category based on name if a category with that name exists
     * @param name - String
     * @return - response message
     */
    public String deleteCategoryByName(String name) {
        Category existingCategory = categoryRepo.findCategoryByName(name).orElse(null);

        if (existingCategory != null) {
            categoryRepo.deleteCategoryByName(name);
            return "Category with name " + name + " was deleted.";
        } else {
            throw new IllegalArgumentException("No category with that name");
        }
    }

    /**
     * adds multiple categories if the categories doesn't already exists
     * @param newCategories - Objects
     * @return - response message
     */
    public String addCategories(List<Category> newCategories) {
        List<Category> allExistingCategories = categoryRepo.findAll();

        boolean anyMatch = newCategories.stream()
                .anyMatch(new HashSet<>(allExistingCategories)::contains);

        if (anyMatch) {
            throw new IllegalArgumentException("Check so no category names already exists");
        } else {
             categoryRepo.saveAll(newCategories);
             String resp = "";
             for (Category cat : newCategories) {
                 resp = resp + cat.getName() + ", ";
             }
             resp = resp + "was added";
             return resp;

        }

    }
}
