package com.example.gruppprojekt.service;

import com.example.gruppprojekt.model.Author;
import com.example.gruppprojekt.repo.AuthorRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

/**
 * Created by Hodei Eceiza
 * Date: 5/19/2021
 * Time: 13:05
 * Project: GRUPP_PROJEKT
 * Copyright: MIT
 */
@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    List<Author> mockList;
    @Mock
    AuthorRepo mockAuthorRepo;

    @InjectMocks
    AuthorService authorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Author author1 = new Author("id1", LocalDateTime.now(), LocalDateTime.now());
        Author author2 = new Author("id2", LocalDateTime.now(), LocalDateTime.now());

        author1.setFirstName("KG");
        author1.setLastName("Johansson");
        author1.setDateOfBirth("1970-02-02");
        author2.setFirstName("Sigmund");
        author2.setLastName("Freud");
        author2.setDateOfBirth("1856-05-06");


        mockList = new ArrayList<>(Arrays.asList(author1, author2));

    }

    @Test
    @DisplayName("add an author, if add author returns added author is success")
    void addAuthor() {
        Mockito.when(mockAuthorRepo.save(any())).thenReturn(mockList.get(0));
        Author expected = mockList.get(0);
        Author actual = authorService.addAuthor(mockList.get(0));
        assertEquals(expected, actual);
        //-----falsifiability test//
        expected = mockList.get(1);
        assertNotEquals(expected, actual);
    }

    @Test
    @DisplayName("get a list of authors, if the mockuplist is returned then is success")
    void getAllAuthors() {
        Mockito.when(mockAuthorRepo.findAll()).thenReturn(mockList);
        List<Author> expected = mockList;
        List<Author> actual = authorService.getAllAuthors();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Delete an author by id, if confirmation string is returned then is success")
    void deleteAuthorById() {
        Mockito.when(mockAuthorRepo.findById(mockList.get(0).getId())).thenReturn(java.util.Optional.ofNullable(mockList.get(0)));
        String expected = "Author deleted with " + mockList.get(0).getId() + " and named: " + mockList.get(0).getFirstName() + ", " + mockList.get(0).getLastName();
        String actual = authorService.deleteAuthorById(mockList.get(0).getId());
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("update an author throws exception when misses property")
    void updateAuthorThrowsExceptionWhenMissesProperty() {


        Author authorNoName = mockList.get(0);
        authorNoName.setFirstName(null);
        assertThrows(Exception.class, () -> authorService.updateAuthor(authorNoName), "author without name");


    }

    @Test
    @DisplayName("update an author throws exception if it doesn't exist in db")
    void updateAuthorThrowsExceptionWhenAuthorNotInDB() {

        Mockito.when(mockAuthorRepo.findById(mockList.get(0).getId())).thenReturn(java.util.Optional.ofNullable(mockList.get(0)));
        Author authorNotInDB = mockList.get(0);
        authorNotInDB.setId("NoIdInDB");
        assertThrows(Exception.class, () -> authorService.updateAuthor(authorNotInDB), "author not in db");

    }

    @Test
    @DisplayName("updates an author without sending creation date")
    void updateAuthorWithoutCreateDateReturnsWithCreateDate() {
        Mockito.when(mockAuthorRepo.findById(mockList.get(0).getId())).thenReturn(java.util.Optional.ofNullable(mockList.get(0)));
        Mockito.when(mockAuthorRepo.save(any())).thenReturn(mockList.get(0));

        //--Expected
        Author expected = mockAuthorRepo.save(mockList.get(0));
        try {
            Author authorNoCreateDate = mockList.get(0);
            authorNoCreateDate.setCreatedDate(null);
            Author actual = authorService.updateAuthor(authorNoCreateDate);
            assertEquals(expected, actual);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    @DisplayName("update an authors first name")
    void updateAuthorsFirstName() {
        Mockito.when(mockAuthorRepo.findById(mockList.get(0).getId())).thenReturn(java.util.Optional.ofNullable(mockList.get(0)));
        //--Expected
        Author authorChangeName = mockList.get(0);
        authorChangeName.setFirstName("testName");
        Author expected = mockAuthorRepo.save(authorChangeName);
        //--actual
        Author actual = null;
        try {

            actual = authorService.updateAuthor(authorChangeName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //--result
        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("send a list of authors and return all the authors")
    void addAuthors() {
        Mockito.when(mockAuthorRepo.saveAll(any())).thenReturn(mockList);

        List<Author> listToSend = Arrays.asList(new Author("id3", LocalDateTime.now(), LocalDateTime.now()), new Author("id4", LocalDateTime.now(), LocalDateTime.now()));
        mockList.addAll(listToSend);

        List<Author> expected = mockList;

        List<Author> actual = authorService.addAuthors(listToSend);

        assertEquals(expected, actual);

    }
}
