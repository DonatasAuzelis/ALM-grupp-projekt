package com.example.gruppprojekt.controller;

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
    public ResponseEntity<Object> addCategory(@RequestBody Category category) {
        try {
        return ResponseEntity.ok(categoryService.addCategory(category));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/addCategories")
    public ResponseEntity<Object> addCategories(@RequestBody List<Category> categories) {
        try {


        return ResponseEntity.ok(categoryService.addCategories(categories));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCategoryById(@PathVariable String id) {
        try {

        return ResponseEntity.ok(categoryService.deleteCategoryById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<Object> updateCategory(@RequestBody Category category, @PathVariable String id) {
        try {
            return ResponseEntity.ok(categoryService.updateCategory(category,id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
