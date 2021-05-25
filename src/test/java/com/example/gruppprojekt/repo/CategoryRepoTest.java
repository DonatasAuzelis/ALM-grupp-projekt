package com.example.gruppprojekt.repo;

import com.example.gruppprojekt.model.Category;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Max Erling
 * Date: 2021-05-19
 * Copyright: MIT
 * Class: Java20B
 */
@ExtendWith(SpringExtension.class)
@DataMongoTest
class CategoryRepoTest {

    @Autowired
    CategoryRepo categoryRepo;
    private Category c;

    @BeforeEach
    public void init() {
        c = new Category("2","Drama", new Date().toString(),new Date().toString());
        categoryRepo.save(c);
    }

    @Nested
    @DisplayName("deleteCategoryByName method")
    class deleteCategoryByName {

    @Test
    @DisplayName("Testing viable input")
    void deleteCategoryByName_Successful() {
        assertEquals(c,categoryRepo.findCategoryByName("Drama").get());
        categoryRepo.deleteCategoryByName("Drama");
        assertNotEquals(c,categoryRepo.findCategoryByName("Drama").orElse(null));

    }

    @Test
    @DisplayName("Testing viable input but wrong name")
    void deleteCategoryByName_Unsuccessful(){
        assertEquals(null,categoryRepo.deleteCategoryByName("Fantasy").orElse(null));
        assertNotEquals(categoryRepo.findCategoryByName("Drama").get(), categoryRepo.deleteCategoryByName("Fantasy").orElse(null));
    }

    }


    @Nested
    @DisplayName("findCategoryByName method")
    class findCategoryByName {

    @Test
    @DisplayName("Testing viable input")
    void findCategoryByName_Successful() {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(categoryRepo.findCategoryByName("Drama").get());
        assertEquals(true,categoryList.contains(c));
        assertNotEquals("Fantasy",categoryList.get(0).getName());

    }

    @Test
    @DisplayName("Testing viable input but wrong name")
    void findCategoryByName_Unsuccessful() {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(categoryRepo.findCategoryByName("Fantasy").orElse(null));
        assertEquals(true,categoryList.contains(null));
    }
    }
}