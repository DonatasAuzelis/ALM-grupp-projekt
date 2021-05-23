package com.example.gruppprojekt.repo;

import com.example.gruppprojekt.model.Author;
import com.example.gruppprojekt.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class BookRepoTest {

    @Autowired
    private BookRepo mockBookRepo;
    @Autowired
    private AuthorRepo mockAuthorRepo;

    private Author mockAuthor1;
    private Book mockBook1;
    private List<Book> mockBookList;

    @BeforeEach
    void init() {
        mockAuthor1 = new Author();
        mockAuthor1.setFirstName("Plato");
        mockAuthor1.setLastName("Greek");

        mockAuthorRepo.save(mockAuthor1);

        mockBook1 = new Book();
        mockBook1.setTitle("Republic");
        mockBook1.setAuthor(mockAuthor1);
        mockBook1.setPrice(500.50);
        mockBook1.setPageCount(450);

        mockBookList = new ArrayList<>();
        mockBookList.add(mockBook1);

        mockBookRepo.save(mockBook1);
    }

    @Test
    @DisplayName("Test green if returns book list of correct author")
    void findBooksByAuthor() {
        List<Book> expected = mockBookRepo.findBooksByAuthor(mockAuthor1);
        List<Book> actual = mockBookList;

        assertEquals(expected.get(0).getAuthor().getId(), actual.get(0).getAuthor().getId());
    }

    @Test
    @DisplayName("Test green if returns book with correct title")
    void findBookByTitle() {
        Book expected = mockBookRepo.findBookByTitle(mockBookList.get(0).getTitle());
        Book actual = mockBook1;

        assertEquals(expected.getTitle(), actual.getTitle());
    }

    @Test
    @DisplayName("Test green if returns book with correct id")
    void findBookById() {
        Book expected = mockBookRepo.findBookById(mockBookList.get(0).getId());
        Book actual = mockBook1;

        assertEquals(expected.getId(), actual.getId());
    }

}