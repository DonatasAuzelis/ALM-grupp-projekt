package com.example.gruppprojekt.service;

import com.example.gruppprojekt.model.Category;
import com.example.gruppprojekt.repo.CategoryRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * Created by Max Erling
 * Date: 2021-05-20
 * Copyright: MIT
 * Class: Java20B
 */
@ExtendWith(SpringExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepo categoryRepo;

    @InjectMocks
    private CategoryService categoryService;

    private List<Category> categories;

    @BeforeEach
    public void init() {
        openMocks(this);
        Category c1 = new Category("10","Thriller",new ArrayList<>(), LocalDate.now(), LocalDate.now());
        Category c2 = new Category("2","Romance",new ArrayList<>(), LocalDate.now(), LocalDate.now());
        categories  = new ArrayList<>();
        categories.add(c1);
        categories.add(c2);

    }

    @Nested
    @DisplayName("Testing addCategory method")
    class AddCateogryTest {
        @Test
        @DisplayName("Adds a new category with valid input")
        void addCategory() {
            when(categoryRepo.save(any())).thenReturn(categories.get(0));
            Category expected = categories.get(0);
            Category actual = categoryService.addCategory(categories.get(0));

            assertEquals(expected,actual);

            verify(categoryRepo, times(1)).save(any());
        }
    }

    @Nested
    @DisplayName("Testing updateCategory method")
    class updateCategoryTest {
        @Test
        @DisplayName("Updates an existing category")
        void updateCategoryTest() {
            Category updatedCategory = new Category("10","Fantasy",new ArrayList<>(), LocalDate.now(), LocalDate.now());
            when(categoryRepo.findById(categories.get(0).getId())).thenReturn(java.util.Optional.ofNullable(categories.get(0)));
            when(categoryRepo.findCategoryByName(categories.get(0).getName())).thenReturn(java.util.Optional.ofNullable(categories.get(0)));
            when(categoryRepo.save(any())).thenReturn(updatedCategory);

            Category actual = categoryService.updateCategory(updatedCategory,categories.get(0).getId());
            assertEquals(updatedCategory, actual);

        }
    }


    @Nested
    @DisplayName("Testing getAllCategories method")
    class getAllCategoriesTest {
        @Test
        @DisplayName("Get all categories")
        void getAllCategoriesTest() {
            when(categoryRepo.findAll()).thenReturn(categories);
            List<Category> expected = categories;
            List<Category> actual = categoryService.getAllCategories();

            assertEquals(expected,actual);
            verify(categoryRepo, times(1)).findAll();
        }
    }


    @Nested
    @DisplayName("Testing getCategoryById method")
    class getCategoryByIdTest {
        @Test
        @DisplayName("Get category based on a valid id")
        void getCategoryByIdTest() {
            when(categoryRepo.findById("10")).thenReturn(java.util.Optional.ofNullable(categories.get(0)));
            Category expected = categories.get(0);
            Category actual = categoryService.getCategoryById("10");

            assertEquals(expected,actual);
            verify(categoryRepo, times(2)).findById(any());
        }
    }


    @Nested
    @DisplayName("Testing deleteCategoryById method")
    class deleteCategoryByIdTest {
        @Test
        @DisplayName("Delete existing category based on a valid id")
        void deleteCategoryByIdTest() {
            when(categoryRepo.findById("10")).thenReturn(java.util.Optional.ofNullable(categories.get(0)));
            String expected = "Category with id 10 was deleted.";
            String actual = categoryService.deleteCategoryById(categories.get(0).getId());
            assertEquals(expected,actual);


        }
    }



    @Nested
    @DisplayName("Testing deleteCategoryByName method")
    class deleteByNameTest {
        @Test
        @DisplayName("Delete an existing category based on name")
        void deleteCategoryByNameTest() {
            when(categoryRepo.findCategoryByName(categories.get(0).getName())).thenReturn(java.util.Optional.ofNullable(categories.get(0)));
            String expected = "Category with name " + categories.get(0).getName() + " was deleted.";
            String actual = categoryService.deleteCategoryByName(categories.get(0).getName());
            assertEquals(expected,actual);
        }
    }



    @Nested
    @DisplayName("Testing addCategories method")
    class addCategoriesTest {
        @Test
        @DisplayName("Add multiple categories")
        void addCategoriesTest() {
            Category c3 = new Category("30","Drama",new ArrayList<>(), LocalDate.now(),LocalDate.now());
            Category c4 = new Category("25","Comedy", new ArrayList<>(), LocalDate.now(),LocalDate.now());
            List<Category> newCategories = new ArrayList<>();
            newCategories.add(c3);
            newCategories.add(c4);

            assertEquals(categoryService.addCategories(newCategories),"Drama, Comedy, was added");


        }
    }


}