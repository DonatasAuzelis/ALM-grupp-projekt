package com.example.gruppprojekt.service;

import com.example.gruppprojekt.repo.BookRepo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {


    @BeforeEach
    void beforeEachMethod() {

    }

    //måste vara static
    @BeforeAll
    static void init() {
        System.out.println("Innan start av test klassen");
    }

    //after - clean up data, close connections
    @AfterEach
    void afterEachMethod() {
        System.out.println("After method");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After test class");
    }

    @Test
    @DisplayName("")
    void addBook() {
    }

    @Test
    @DisplayName("")
    void getAllBooks() {
    }

    @Test
    @DisplayName("")
    void deleteBookById() {
    }

    @Test
    @DisplayName("")
    void updateBook() {
    }

    @Test
    void addBooks() {

    }

    @Test
    @DisplayName("")
    void getBooksByAuthor() {
        //testa med forloop
    }

    @Test
    @DisplayName("")
    void getBookByTitle() {

    }
}