package com.example.gruppprojekt.controller;

import com.example.gruppprojekt.model.Book;
import com.example.gruppprojekt.model.Category;
import com.example.gruppprojekt.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.addCategory(category));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable String id) {
        return ResponseEntity.ok(categoryService.deleteCategoryById(id));
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable String id, @RequestBody Category category) {
        try {
            return ResponseEntity.ok(categoryService.updateCategory(id,category));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
