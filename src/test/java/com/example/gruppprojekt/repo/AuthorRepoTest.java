package com.example.gruppprojekt.repo;

import com.example.gruppprojekt.model.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Hodei Eceiza
 * Date: 5/20/2021
 * Time: 00:04
 * Project: GRUPP_PROJEKT
 * Copyright: MIT
 */
@ExtendWith(SpringExtension.class)
@DataMongoTest
class AuthorRepoTest {

    @Autowired
    private AuthorRepo authorRepo;

    private List<Author> mockupList;

    @BeforeEach
    public void setUp(){
        Author author1=new Author("id1", LocalDate.now(),LocalDate.of(2021,6,7));
        Author author2=new Author("id2", LocalDate.now(),LocalDate.of(2021,6,6));

        author1.setFirstName("ABC");
        author1.setLastName("ABC");
        author1.setDateOfBirth("1970-02-02");
        author2.setFirstName("AAC");
        author2.setLastName("AAC");
        author2.setDateOfBirth("1856-05-06");


        mockupList= new ArrayList<>(Arrays.asList(author1, author2));
        authorRepo.saveAll(mockupList);
    }
    @Test
    @DisplayName("If gives ordered last modified date is success")
    void findAllByOrderByLastModifiedDesc() {


    }
    @Test
    @DisplayName("If gives ordered by name is success")
    void findAllByOrderByFirstNameDesc() {
        List<Author>actual=authorRepo.findAll(Sort.by("firstName").descending());
        List<Author> expect=mockupList;
        assertEquals(expect,actual);
        //--falsifiability->
        expect=mockupList.stream().sorted(Comparator.comparing(Author::getFirstName)).collect(Collectors.toList());
        assertNotEquals(expect,actual);
    }

    @Test
    @DisplayName("If gives ordered by last name is success")
    void findAllByOrderByLastNameDesc() {
        List<Author>actual=authorRepo.findAll(Sort.by("lastName").descending());
        List<Author> expect=mockupList;
        assertEquals(expect,actual);
        //--falsifiability->
        expect=mockupList.stream().sorted(Comparator.comparing(Author::getLastName)).collect(Collectors.toList());
        assertNotEquals(expect,actual);

    }


}