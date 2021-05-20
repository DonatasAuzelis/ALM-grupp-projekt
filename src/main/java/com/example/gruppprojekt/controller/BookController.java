package com.example.gruppprojekt.controller;

import com.example.gruppprojekt.model.Author;
import com.example.gruppprojekt.model.Book;
import com.example.gruppprojekt.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller with CRUD- methods acting through BookService class and manipulating data in a Mongo database
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * adds a book
     * @param book Book Object
     * @return book to be added
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addBook(@RequestBody Book book) {
        try {
            return ResponseEntity.ok(bookService.addBook(book));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * adds a list of books
     * @param books List of Book
     * @return list of books to be added
     */
    @PostMapping("/addBooks")
    public ResponseEntity<List<Book>> addBooks(@RequestBody List<Book> books) {
        return ResponseEntity.ok(bookService.addBooks(books));
    }

    /**
     * returns all books
     * @return list of all books in database
     */
    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    /**
     * deletes a book by id
     * @param id String Book id
     * @return book to be deleted
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(bookService.deleteBookById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * updates information of a book by id
     * @param id String Book id
     * @param book Book Object
     * @return book Object to be updated, else exception
     */
    @PostMapping("/update/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable String id, @RequestBody Book book) {

        try {
            return ResponseEntity.ok(bookService.updateBook(id, book));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * returns books by a specific author
     * @param author Author Object
     * @return book(s) by a specified author
     */
    @GetMapping("/getBooksByAuthor")
    public ResponseEntity<Object> getBooksByAuthor(@RequestBody Author author ) {
        return ResponseEntity.ok(bookService.getBooksByAuthor(author));
    }

    /**
     * returns a book with a specific title
     * @param title String Book title
     * @return book by a specified title
     */
    @GetMapping("/getBookByTitle/{title}")
    public ResponseEntity<Object> getBookByTitle(@PathVariable String title) {
        try {
            return ResponseEntity.ok(bookService.getBookByTitle(title));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
