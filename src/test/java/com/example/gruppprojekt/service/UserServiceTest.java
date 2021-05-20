package com.example.gruppprojekt.service;

import com.example.gruppprojekt.model.Book;
import com.example.gruppprojekt.model.Users;
import com.example.gruppprojekt.repo.UserRepository;
import com.example.gruppprojekt.util.UserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;



/**
 * Created by Ashkan Amiri
 * Date:  2021-05-18
 * Time:  21:59
 * Project: ALM-grupp-projekt
 * Copyright: MIT
 */
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    List<Users> users = new ArrayList<>();

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Book b = new Book();
        Book b1 = new Book();
        b.setTitle("title");
        b1.setTitle("titel1");
        List<Book> books = Arrays.asList(b, b1);
        users.add(new Users("Ashkan","lastname","date","id",
                123456789L,"pass","email",books,LocalDateTime.now(), LocalDateTime.now()));
        users.add(new Users("user_id", 9108301111L, "123456ashkan",
                "ashkan@gmail.com", books, LocalDateTime.now(), LocalDateTime.now()));
        users.add(new Users("user_id2", 9108302222L, "1234ashkan",
                "ashkanTest@gmail.com", null, LocalDateTime.now(), LocalDateTime.now()));
    }

    @Test
    @DisplayName("add a user-->Successful test")
    void addUserTest() throws UserException {
        Mockito.when(repository.save(any())).thenReturn(users.get(0));
        Users expected = users.get(0);
        Users actual = userService.addUser(users.get(0));
        assertEquals(expected,actual,"Should be same");
    }

    @Test
    @DisplayName("Get all users-->Successful test")
    void getAllUsersTest() {
        Mockito.when(repository.findAll()).thenReturn(users);
        List<Users> expected = users;
        List<Users> actual = userService.getAllUsers();
        assertEquals(expected,actual,"Should be same");
    }

    @Test
    @DisplayName("Delete a user-->Successful test")
    void deleteUserByIdTest() {
        Mockito.when(repository.findById(any())).thenReturn(java.util.Optional.ofNullable(users.get(0)));
        String expected = "User deleted with social security number: " + users.get(0).getPersonalNumber() +
                "\n and named: " + users.get(0).getFirstName() + ", " + users.get(0).getLastName();
        String actual = userService.deleteUserById(users.get(0).getId());
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Update user profile-->Successful test")
    void updateUserProfileTest() throws UserException {
        Mockito.when(repository.findById(any())).thenReturn(java.util.Optional.ofNullable(users.get(0)));
        users.get(0).setLastName("lastnameTest");
        System.out.println(users.get(0));
        Users expected = users.get(0);
        //--------------------------------------------------------------------
        Mockito.when(repository.save(any())).thenReturn(users.get(0));
        Users actual = userService.updateUserProfile(users.get(0));
        //--------------------------------------------------------------------
        assertEquals(expected.getLastName(),actual.getLastName());
    }

    @Test
    @DisplayName("Update user books-->Successful test")
    void updateUserBooksTest() throws UserException {
        Mockito.when(repository.findById(any())).thenReturn(java.util.Optional.ofNullable(users.get(0)));
        Book b = new Book();
        Book b1 = new Book();
        Book b2 = new Book();
        b.setTitle("book1");
        b1.setTitle("book2");
        b2.setTitle("book3");
        List<Book> books = Arrays.asList(b, b1, b2);

        users.get(0).setBooks(books);
        Users expected = users.get(0);
        //--------------------------------------------------------------------
        Mockito.when(repository.save(any())).thenReturn(users.get(0));
        Users actual = userService.updateUserBooks(users.get(0).getId(),users.get(0).getBooks());
        //--------------------------------------------------------------------
        assertEquals(expected.getBooks(),actual.getBooks());
    }

    @Test
    @DisplayName("User Authentication-->Successful test")
    void userAuthenticationTest() throws UserException {
        Mockito.when(repository.
                findUsersByEmailAndPasswordOrPersonalNumberAndPassword(any(),any(),any(),any()))
                .thenReturn(users.get(0));
        Users expected = users.get(0);
        Users actual = userService.UserAuthentication(users.get(0).getEmail(),users.get(0).getPersonalNumber(),
                users.get(0).getPassword());
        assertEquals(expected.getPassword(),expected.getPassword());
        assertEquals(expected.getEmail(),expected.getEmail());
        assertEquals(expected.getPersonalNumber(),expected.getPersonalNumber());
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Get a user by personal number-->Successful test")
    void getByPersonalNrTest() throws UserException {
        Mockito.when(repository.findUsersByPersonalNumber(any())).thenReturn(users.get(0));
        Users expected = users.get(0);
        Users actual = userService.getByPersonalNr(users.get(0).getPersonalNumber());
        assertEquals(expected,actual);
    }
}