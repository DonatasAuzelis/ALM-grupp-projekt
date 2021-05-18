package com.example.gruppprojekt.controller;


import com.example.gruppprojekt.model.Author;
import com.example.gruppprojekt.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        return ResponseEntity.ok(authorService.addAuthor(author));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Author>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAuthorById(@PathVariable String id) {
        return ResponseEntity.ok(authorService.deleteAuthorById(id));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Object> updateAuthor(@PathVariable String id, @RequestBody Author author) {

        try {
            return ResponseEntity.ok(authorService.updateAuthor(id, author));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
