package com.example.gruppprojekt.controller;

import com.example.gruppprojekt.model.Author;
import com.example.gruppprojekt.model.Book;
import com.example.gruppprojekt.repo.BookRepo;
import com.example.gruppprojekt.service.BookService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }

    @PostMapping("/addBooks")
    public ResponseEntity<List<Book>> addBooks(@RequestBody List<Book> books) {
        return ResponseEntity.ok(bookService.addBooks(books));
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

    @GetMapping("/getBooksByAuthor")
    public ResponseEntity<Object> getBooksByAuthor(@RequestBody Author author ){
        return ResponseEntity.ok(bookService.getBooksByAuthor(author));
    }

}
