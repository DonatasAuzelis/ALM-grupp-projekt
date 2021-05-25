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


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

import static java.util.stream.Collectors.toList;

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
    public void setUp() {
        Author author1 = new Author("id1", LocalDateTime.now(), LocalDateTime.now());
        Author author2 = new Author("id2", LocalDateTime.now(), LocalDateTime.now());

        author1.setFirstName("ABC");
        author1.setLastName("ABC");
        author1.setDateOfBirth("1970-02-02");
        author2.setFirstName("AAC");
        author2.setLastName("AAC");
        author2.setDateOfBirth("1856-05-06");


        mockupList = new ArrayList<>(Arrays.asList(author1, author2));
        authorRepo.saveAll(mockupList);
    }

    @Test
    @DisplayName("If gives ordered last modified date is success")
    void findAllByOrderByLastModifiedDesc() {
        //take from repository and get the ids as String to compare
        List<Author> repolist = authorRepo.findAllByOrderByLastModifiedDateDesc();
        List<String> actual = repolist.stream().map(Author::getId).collect(toList());

        //Create a custom comparator, get from the ids from the list sorted by last modified date
        Comparator<Author> byLastModifiedDate = Comparator.comparingLong(a -> a.getLastModifiedDate().toInstant(ZoneOffset.UTC).toEpochMilli());
        List<String> expect = mockupList.stream()
                .sorted(byLastModifiedDate.reversed())
                .map(Author::getId)
                .collect(toList());

        //test if they are equals
        assertEquals(expect, actual);

        //--falsifiability->
        //sort in Ascendant
        expect = mockupList.stream()
                .sorted(byLastModifiedDate)
                .map(Author::getId)
                .collect(toList());

        //test they are not equals
        assertNotEquals(expect, actual);
    }

    @Test
    @DisplayName("If gives ordered by name is success")
    void findAllByOrderByFirstNameDesc() {
        List<Author> repolist = authorRepo.findAll(Sort.by("firstName").descending());
        List<String> actual = repolist.stream().map(Author::getFirstName).collect(toList());

        List<String> expect = mockupList.stream().map(Author::getFirstName).collect(toList());

        assertEquals(expect, actual);

        //--falsifiability->
        expect = mockupList.stream().sorted(Comparator.comparing(Author::getFirstName)).map(Author::getFirstName).collect(toList());
        assertNotEquals(expect, actual);


    }

    @Test
    @DisplayName("If gives ordered by last name is success")
    void findAllByOrderByLastNameDesc() {
        List<Author> repolist = authorRepo.findAll(Sort.by("lastName").descending());
        List<String> actual = repolist.stream().map(Author::getLastName).collect(toList());

        List<String> expect = mockupList.stream().map(Author::getLastName).collect(toList());

        assertEquals(expect, actual);

        //--falsifiability->
        expect = mockupList.stream().sorted(Comparator.comparing(Author::getLastName)).map(Author::getLastName).collect(toList());
        assertNotEquals(expect, actual);

    }


}