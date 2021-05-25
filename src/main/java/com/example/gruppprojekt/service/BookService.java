package com.example.gruppprojekt.service;

import com.example.gruppprojekt.model.Author;
import com.example.gruppprojekt.model.Book;
import com.example.gruppprojekt.repo.AuthorRepo;
import com.example.gruppprojekt.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private AuthorRepo authorRepo;

    /**
     * adds book object to repo
     * @param book Book object
     * @return book to be added
     */
    public Book addBook(Book book) throws Exception {
        Book bookCheck = bookRepo.findBookByTitle(book.getTitle());
        if (bookCheck == null) {
            return bookRepo.save(book);
        }
       throw new Exception("Something went wrong, check if book already exists!");
    }

    /**
     * returns list of books in repo
     * @return list of all books
     */
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    /**
     * deletes book by id
     * @param id String book id
     * @return String confirmation of deleted book id
     */
    public String deleteBookById(String id) throws Exception {
        Book bookCheck = bookRepo.findBookById(id);
        if (bookCheck != null) {
            bookRepo.deleteById(id);
            return "Book with id: " + id + " was deleted!";
        }
        throw new Exception("Could find book with id: " + id + ", try again.");
    }

    /**
     * updates book info
     * @param id String book id
     * @param book Book object
     * @return book Object in repo
     * @throws ClassNotFoundException response message
     */
    public Book updateBook(String id, Book book) throws ClassNotFoundException {
        Optional<Book> newBook = bookRepo.findById(id);

        if (newBook.isPresent()) {
            newBook.get().setAuthor(book.getAuthor());
            newBook.get().setTitle(book.getTitle());
            newBook.get().setPrice(book.getPrice());
            newBook.get().setPageCount(book.getPageCount());

            return bookRepo.save(newBook.get());
        } else {
            throw new ClassNotFoundException("Something went wrong!");
        }
    }

    /**
     * adds a list of books to repo
     * @param books List of books
     * @return books that were added
     */
    public List<Book> addBooks(List<Book> books) {
        return bookRepo.saveAll(books);
    }

    /**
     * returns a list of books of a specific author
     * @param author Author Object
     * @return book list of a specified author
     */
    public List<Book> getBooksByAuthor(Author author) {
        return bookRepo.findBooksByAuthor(author);
    }

    /**
     * returns a book with a specific title
     * @param title String
     * @return book with specified title
     */
    public Book getBookByTitle(String title) throws Exception {
        Book bookCheck = bookRepo.findBookByTitle(title);
        if (bookCheck != null && bookCheck.getTitle().equals(title)) {
            return bookRepo.findBookByTitle(title);
        }
        throw new Exception("Something went wrong, double check the title!");
    }
}
