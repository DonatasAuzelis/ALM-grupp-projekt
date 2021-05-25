package com.example.gruppprojekt.repo;

import com.example.gruppprojekt.model.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Created by Ashkan Amiri
 * Date:  2021-05-18
 * Time:  22:01
 * Project: ALM-grupp-projekt
 * Copyright: MIT
 */
@ExtendWith(SpringExtension.class)
@DataMongoTest
class UserRepositoryTest {
    private Users user;

    @Autowired
    private UserRepository repository;

    @BeforeEach
    void setUp() {
        user = new Users("firstName", "lastname", "date", "id",
                123456789L, "pass", "email",
                null, new Date().toString(),
                new Date().toString());
        repository.save(user);
    }

    @Test
    void findUsersByPersonalNumberTest() {
        Users actual = repository.findUsersByPersonalNumber(user.getPersonalNumber());
        Users expect = user;
        assertEquals(expect.getId(), actual.getId());
        assertEquals(expect.getPersonalNumber(), actual.getPersonalNumber());
        assertEquals(expect.toString(),actual.toString());
        assertEquals(expect.getCreatedDate(),actual.getCreatedDate());
        System.out.println(expect.getCreatedDate());
        System.out.println(actual.getCreatedDate());
        System.out.println("**************************************************************");
    }
    @Test
    void findUsersByEmailOrPersonalNumberTest() {
        Users actual = repository.findUsersByEmailOrPersonalNumber(user.getEmail(), user.getPersonalNumber());
        Users expect = user;
        assertEquals(expect.getId(), actual.getId());
        assertEquals(expect.getEmail(), actual.getEmail());
        assertEquals(expect.getPersonalNumber(), actual.getPersonalNumber());
    }

    @Test
    void findUsersByEmailAndPasswordOrPersonalNumberAndPasswordTest() {
        Users actual = repository.
                findUsersByEmailAndPasswordOrPersonalNumberAndPassword
                        (user.getEmail(), user.getPassword(), user.getPersonalNumber(), user.getPassword());
        Users expect = user;
        assertEquals(expect.getId(), actual.getId());
        assertEquals(expect.getEmail(), actual.getEmail());
        assertEquals(expect.getPersonalNumber(), actual.getPersonalNumber());
    }
}