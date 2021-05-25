package com.example.gruppprojekt.service;

import com.example.gruppprojekt.model.Author;
import com.example.gruppprojekt.model.Book;
import com.example.gruppprojekt.repo.BookRepo;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    private List<Book> mockBookList;
    private List<Author> mockAuthorList;

    @Mock
    private BookRepo mockBookRepo;

    @InjectMocks
    private BookService mockBookService;

    private Author mockAuthor1, mockAuthor2;
    private Book mockBook1, mockBook2;


    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);

        mockBookList = new ArrayList<>();
        mockAuthorList = new ArrayList<>();

        mockAuthor1 = new Author();
        mockAuthor1.setId("abc");
        mockAuthor1.setFirstName("Plato");
        mockAuthor1.setLastName("Greek");

        mockAuthor2 = new Author();
        mockAuthor2.setId("123");
        mockAuthor2.setFirstName("Albert");
        mockAuthor2.setLastName("Camus");

        mockAuthorList.add(mockAuthor1);
        mockAuthorList.add(mockAuthor2);

        mockBook1 = new Book();
        mockBook1.setId("abc");
        mockBook1.setTitle("Republic");
        mockBook1.setAuthor(mockAuthor1);
        mockBook1.setPrice(500.50);
        mockBook1.setPageCount(450);

        mockBook2 = new Book();
        mockBook2.setId("123");
        mockBook2.setTitle("The Stranger");
        mockBook2.setAuthor(mockAuthor2);
        mockBook2.setPrice(249.99);
        mockBook2.setPageCount(100);

        mockBookList.add(mockBook1);
        mockBookList.add(mockBook2);
    }

    @Test
    @DisplayName("Test green if a book is added")
    void addBook() throws Exception {
        when(mockBookRepo.save(any())).thenReturn(mockBookList.get(0));
        Book expected = mockBookList.get(0);
        Book actual = mockBookService.addBook(mockBookList.get(0));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test green if book list is returned")
    void getAllBooks() {
        when(mockBookRepo.findAll()).thenReturn(mockBookList);
        List<Book> expected = mockBookList;
        List<Book> actual = mockBookService.getAllBooks();
        assertEquals(expected, actual);
    }

    @Test
    @SneakyThrows
    @DisplayName("Test green if a book is deleted")
    void deleteBookById() {
        when(mockBookRepo.findBookById(any())).thenReturn(mockBookList.get(0));
        String expected = "Book with id: " + mockBookList.get(0).getId() + " was deleted!";
        String actual = mockBookService.deleteBookById(mockBookList.get(0).getId());
        assertEquals(expected, actual);

    }

    @Test
    @Disabled
    @SneakyThrows
    @DisplayName("Test green if a book information was updated")
    void updateBook() {
        when(mockBookRepo.findBookById(any())).thenReturn(mockBookList.get(0));
        Book expected = mockBookList.get(0);
        expected.setTitle("Test Title");
        System.out.println("expected " + expected);

        String id = mockBookList.get(0).getId();
        Book actual = mockBookService.updateBook(id, mockBookList.get(0));
        System.out.println("actual " + actual);

        assertEquals(expected.getTitle(), actual.getTitle());
    }

    @Test
    @DisplayName("Test green if books are added to list")
    void addBooks() {
        when(mockBookRepo.saveAll(any())).thenReturn(mockBookList);

        List<Book> expected = mockBookList;
        Book mockBook1 = new Book();
        mockBook1.setTitle("Republic");
        mockBook1.setPrice(500.50);
        mockBook1.setPageCount(450);

        Book mockBook2 = new Book();
        mockBook2.setTitle("The Stranger");
        mockBook2.setPrice(249.99);
        mockBook2.setPageCount(100);

        List<Book> actual = mockBookService.addBooks(Arrays.asList(mockBook1, mockBook2));

        assertEquals(expected, actual);
    }

    @Test
    @Disabled
    @SneakyThrows
    @DisplayName("Test green if returns book list by correct author")
    void getBooksByAuthor() {
        when(mockBookRepo.findBooksByAuthor(any())).thenReturn(mockBookList);

        List<Book> expected = Collections.singletonList(mockBookList.get(0));
        List<Book> actual = mockBookService.getBooksByAuthor(mockAuthor1);

        assertEquals(expected, actual);
    }

    @Test
    @SneakyThrows
    @DisplayName("Test green if returns book with correct title")
    void getBookByTitle() {
        when(mockBookRepo.findBookByTitle(any())).thenReturn(mockBookList.get(0));

        Book expected = mockBookList.get(0);
        Book actual = mockBookService.getBookByTitle("Republic");

        assertEquals(expected.getTitle(), actual.getTitle());

    }
}