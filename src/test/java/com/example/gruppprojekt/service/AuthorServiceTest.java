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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        Author author1=new Author("id1", LocalDate.now(),LocalDate.now());
        Author author2=new Author("id2", LocalDate.now(),LocalDate.now());

        author1.setFirstName("KG");
        author1.setLastName("Johansson");
        author1.setDateOfBirth("1970-02-02");
        author2.setFirstName("Sigmund");
        author2.setLastName("Freud");
        author2.setDateOfBirth("1856-05-06");


        mockList= new ArrayList<>(Arrays.asList(author1, author2));

    }

    @Test
    @ DisplayName("add an author, if add author returns added author is success")
    void addAuthor() {
        Mockito.when(mockAuthorRepo.save(any())).thenReturn(mockList.get(0));
        Author expected=mockList.get(0);
        Author actual=authorService.addAuthor(mockList.get(0));
        assertEquals(expected,actual);
        //-----falsifiability test//
        expected=mockList.get(1);
        assertNotEquals(expected,actual);
    }

    @Test
    @ DisplayName("get a list of authors, if the mockuplist is returned then is success")
    void getAllAuthors() {
        Mockito.when(mockAuthorRepo.findAll()).thenReturn(mockList);
        List<Author>expected=mockList;
        List<Author> actual=authorService.getAllAuthors();
        assertEquals(expected,actual);
    }

    @Test
    @ DisplayName("Delete an author by id, if confirmation string is returned then is success")
    void deleteAuthorById() {
        Mockito.when(mockAuthorRepo.findById(mockList.get(0).getId())).thenReturn(java.util.Optional.ofNullable(mockList.get(0)));
       String expected="Author deleted with " + mockList.get(0).getId() + " and named: " + mockList.get(0).getFirstName() + ", " + mockList.get(0).getLastName();
        String actual=authorService.deleteAuthorById(mockList.get(0).getId());
        assertEquals(expected, actual);
    }

    @Test
    @ DisplayName("update an author throws exception when misses property")
    void updateAuthorThrowsExceptionWhenMissesProperty() {


        Author authorNoName=mockList.get(0);
        authorNoName.setFirstName(null);
        assertThrows(Exception.class,()->{authorService.updateAuthor(authorNoName);},"author without name");
        

    }
    @Test
    @ DisplayName("update an author throws exception if it doesn't exist in db")
    void updateAuthorThrowsExceptionWhenAuthorNotInDB() {

        Mockito.when(mockAuthorRepo.findById(mockList.get(0).getId())).thenReturn(java.util.Optional.ofNullable(mockList.get(0)));
        Author authorNotInDB= mockList.get(0);
       authorNotInDB.setId("NoIdInDB");
        assertThrows(Exception.class,()->{authorService.updateAuthor(authorNotInDB);},"author not in db");

    }
    @Test
    @ DisplayName("update an author without sending creation date")
    void updateAuthorWithoutCreateDateReturnsWithCreateDate() {

    }

    @Test
    void addAuthors() {
    }
}
