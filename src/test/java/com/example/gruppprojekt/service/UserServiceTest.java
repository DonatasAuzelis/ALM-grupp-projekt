package com.example.gruppprojekt.service;

import com.example.gruppprojekt.model.Book;
import com.example.gruppprojekt.model.Users;
import com.example.gruppprojekt.repo.UserRepository;
import com.example.gruppprojekt.util.Encrypt;
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
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


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
                123456789L,"pass","email",books,new Date().toString(), new Date().toString()));
        users.add(new Users("user_id", 9108301111L, "123456ashkan",
                "ashkan@gmail.com", books, new Date().toString(), new Date().toString()));
        users.add(new Users("user_id2", 9108302222L, "1234ashkan",
                "ashkanTest@gmail.com", null, new Date().toString(), new Date().toString()));
    }
    //***********************************************************************************
    //******************************* ADD NEW USER   ************************************
    //***********************************************************************************
    @Test
    @DisplayName("add a user-->Successful test")
    void addUserTest() throws UserException {
        Mockito.when(repository.save(any())).thenReturn(users.get(0));
        Users expected = users.get(0);
        Users actual = userService.addUser(users.get(0));
        assertEquals(expected,actual,"Should be same");
    }
    @Test
    @DisplayName("Add new user->(Given existing user should throw Exception)")
    void addUserTest1(){
        Mockito.when(repository.findUsersByEmailOrPersonalNumber(any(),any())).thenReturn(users.get(0));
        assertThrows(UserException.class,()->userService.addUser(users.get(0)));
    }
    //************************************************************************************
    //******************************* GET ALL USERS   ************************************
    //************************************************************************************
    @Test
    @DisplayName("Get all users-->Successful test")
    void getAllUsersTest() {
        Mockito.when(repository.findAll()).thenReturn(users);
        List<Users> expected = users;
        List<Users> actual = userService.getAllUsers();
        assertEquals(expected,actual,"Should be same");
    }
    @Test
    @DisplayName("Get all users-->check how many times call the method")
    void getAllUsersTest1() {
       repository.findAll();
       verify(repository).findAll();
    }
    //************************************************************************************
    //******************************* DELETE A USER   ************************************
    //************************************************************************************
    @Test
    @DisplayName("Delete a user-->Successful test")
    void deleteUserByIdTest() throws UserException {
        Mockito.when(repository.findById(any())).thenReturn(java.util.Optional.ofNullable(users.get(0)));
        String expected = "User deleted with social security number: " + users.get(0).getPersonalNumber() +
                "\n and named: " + users.get(0).getFirstName() + ", " + users.get(0).getLastName();
        String actual = userService.deleteUserById(users.get(0).getId());
        assertEquals(expected,actual);
    }
    @Test
    @DisplayName("Delete a user-->Given wrong id should throw exception")
    void deleteUserByIdTest1(){
        try {
            userService.deleteUserById("WrongId");
        } catch (UserException e) {
            System.out.println(e.getMessage());
            System.out.println("****************************");
        }
        assertThrows(UserException.class,()->userService.deleteUserById("WrongId"));
    }
    //************************************************************************************
    //******************************* UPDATE A USER   ************************************
    //************************************************************************************
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
    @DisplayName("Update user profile-->Given a null should throw exception")
    void updateUserProfileTest1(){
        assertThrows(UserException.class,()->userService.updateUserProfile(new Users()));
    }
    @Test
    @DisplayName("Update user profile-->Given different personalNR should throw exception")
    void updateUserProfileTest2(){
        Mockito.when(repository.findById(any())).thenReturn(Optional.ofNullable(users.get(1)));
        try {
            userService.updateUserProfile(users.get(0));
        } catch (UserException e) {
            System.out.println(e.getMessage());
            System.out.println("****************************\n" +
                    "############################################");
            //You can't change your social security number
            //* Must be the same as before *
        }
        assertThrows(UserException.class,()->userService.updateUserProfile(users.get(0)));
    }
    @Test
    @DisplayName("Update user profile-->Given NEW PASSWORD should change it")
    void updateUserProfileTest3() throws UserException {
        Mockito.when(repository.findById(any())).thenReturn(Optional.ofNullable(users.get(0)));
        users.get(0).setPassword("new PASSWORD");
        Mockito.when(repository.save(any())).thenReturn(users.get(0));
        Users actual = userService.updateUserProfile(users.get(0));
        assertEquals("60b28ea5e9b2fc5b78e7a061085b5038",actual.getPassword());
        assertEquals(users.get(0).getPassword(),actual.getPassword());
        assertEquals(Encrypt.getMd5("new PASSWORD"),actual.getPassword());
    }

    @Test
    @DisplayName("Update user profile-->Set pass to null shouldn't change it")
    void updateUserProfileTest4() throws UserException {
        Mockito.when(repository.findById(any())).thenReturn(Optional.ofNullable(users.get(0)));
        users.get(0).setPassword(null);
        Mockito.when(repository.save(any())).thenReturn(users.get(0));
        Users actual = userService.updateUserProfile(users.get(0));
        assertEquals(users.get(0).getPassword(),actual.getPassword());
    }

    //************************************************************************************
    //******************************* UPDATE USERS BOOKS  ********************************
    //************************************************************************************
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
    @DisplayName("Remove all books-->Successful test")
    void updateUserBooksTest1() throws UserException {
        Mockito.when(repository.findById(any())).thenReturn(java.util.Optional.ofNullable(users.get(0)));
        List<Book> books = new ArrayList<>(); // För att tomma book list

        users.get(0).setBooks(books);
        Users expected = users.get(0);
        //--------------------------------------------------------------------
        Mockito.when(repository.save(any())).thenReturn(users.get(0));
        Users actual = userService.updateUserBooks(users.get(0).getId(),users.get(0).getBooks());
        //--------------------------------------------------------------------
        assertEquals(expected.getBooks(),actual.getBooks());
    }
    @Test
    @DisplayName("UpdateUserBooks-->Given wrong user id should throw userException")
    void updateUserBooksTest2(){
        Mockito.when(repository.findById(any())).thenReturn(java.util.Optional.empty());
        //--------------------------------------------------------------------
        Mockito.when(repository.save(any())).thenReturn(users.get(0));
        //--------------------------------------------------------------------
        assertThrows(UserException.class,()->
                userService.updateUserBooks(users.get(0).getId(),users.get(0).getBooks()));
    }
    //************************************************************************************
    //******************************* USER AUTHENTICATION   ******************************
    //************************************************************************************
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
    @DisplayName("User Authentication-->Given wrong info should throw user exception")
    void userAuthenticationTest1() {
        Mockito.when(repository.
                findUsersByEmailAndPasswordOrPersonalNumberAndPassword(any(),any(),any(),any()))
                .thenReturn(null);

        assertThrows(UserException.class,()->
                userService.UserAuthentication(users.get(0).getEmail(),users.get(0).getPersonalNumber(),
                        users.get(0).getPassword()));
    }
    //************************************************************************************
    //******************************* GET A USER BY PERSONAL NUMBER  *********************
    //************************************************************************************
    @Test
    @DisplayName("Get a user by personal number-->Successful test")
    void getByPersonalNrTest() throws UserException {
        Mockito.when(repository.findUsersByPersonalNumber(any())).thenReturn(users.get(0));
        Users expected = users.get(0);
        Users actual = userService.getByPersonalNr(users.get(0).getPersonalNumber());
        assertEquals(expected,actual);
    }
    @Test
    @DisplayName("Get a user by personal number-->Given wrong number should throw exception ")
    void getByPersonalNrTest1(){
        Mockito.when(repository.findUsersByPersonalNumber(any())).thenReturn(null);
        assertThrows(UserException.class,()->
                userService.getByPersonalNr(users.get(0).getPersonalNumber()));
    }
}