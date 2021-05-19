package com.example.gruppprojekt.controller;

import com.example.gruppprojekt.model.Book;
import com.example.gruppprojekt.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller with CRUD- methods
 */

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     *
     * @param book
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable String id) {
        return ResponseEntity.ok(bookService.deleteBookById(id));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable String id, @RequestBody Book book) {

        try {
            return ResponseEntity.ok(bookService.updateBook(id, book));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
