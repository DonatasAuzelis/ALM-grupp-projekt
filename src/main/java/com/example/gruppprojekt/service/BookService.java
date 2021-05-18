package com.example.gruppprojekt.service;

import com.example.gruppprojekt.model.Book;
import com.example.gruppprojekt.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public Book addBook(Book book) {
       return bookRepo.save(book);
    }


    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public String deleteBookById(String id) {
        bookRepo.deleteById(id);
        return "Book with id: " + id + " was deleted!";
    }

    public Book updateBook(String id, Book book) throws ClassNotFoundException {
        Optional<Book> newBook = bookRepo.findById(id);

        if (newBook.isPresent()) {
            newBook.get().setAuthor(book.getAuthor());
            newBook.get().setTitle(book.getTitle());
            newBook.get().setPrice(book.getPrice());
            newBook.get().setPageCount(book.getPageCount());

            return bookRepo.save(newBook.get());
        } else {
            throw new ClassNotFoundException();
        }
    }
}
