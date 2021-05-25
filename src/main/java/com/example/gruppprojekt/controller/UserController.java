package com.example.gruppprojekt.controller;

import com.example.gruppprojekt.model.Book;
import com.example.gruppprojekt.model.Users;
import com.example.gruppprojekt.service.UserService;
import com.example.gruppprojekt.util.Encrypt;
import com.example.gruppprojekt.util.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-05-18
 * Time:  10:44
 * Project: ALM-grupp-projekt
 * Copyright: MIT
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    /**
     * Add a user to DB by send info to service
     * Password will be saved as encryption by DIGEST-MD5
     * @param user JSON
     * @return If everything has gone well, a user will be sent in response,
     * otherwise it sends a UserException message
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addUser(@RequestBody Users user) {
        try {
            user.setPassword(Encrypt.getMd5(user.getPassword()));
            return ResponseEntity.ok(userService.addUser(user));
        } catch (UserException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Get all users
     * @return List of users
     */
    @GetMapping("/all")
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * Take users id to delete the user
     * @param id String user id
     * @return String as response
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(userService.deleteUserById(id));
        } catch (UserException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Update user information
     * @param user JSON
     * @return If everything has gone well,a user will be sent in response,
     * otherwise it sends a UserException message
     */
    @PostMapping("/update/profile")
    public ResponseEntity<Object> updateUserProfile(@RequestBody Users user) {
        try {
            return ResponseEntity.ok(userService.updateUserProfile(user));
        } catch (UserException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * @param userID String user ID
     * @param books JSON
     * @return If everything has gone well,a user will be sent in response,
     * otherwise it sends a UserException message
     */
    @PostMapping("/update/books/{userID}")
    public ResponseEntity<Object> updateUserBooks(@PathVariable String userID, @RequestBody List<Book> books) {
        try {
            return ResponseEntity.ok(userService.updateUserBooks(userID, books));
        } catch (UserException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * User authentication for log in
     * @param user JSON
     * @return If everything has gone well,a user will be sent in response,
     * otherwise it sends a UserException message
     */
    @PostMapping(value = "/authentication")
    public ResponseEntity<Object> UserAuthentication(@RequestBody Users user) {
        Users u = null;
        try {
            u = userService.UserAuthentication
                    (user.getEmail(), user.getPersonalNumber(), Encrypt.getMd5(user.getPassword()));
        } catch (UserException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(u);
    }

    /**
     * Get user by personal number
     * @param nr Long personal number
     * @return If everything has gone well,a user will be sent in response,
     * otherwise it sends a UserException message
     */
    @GetMapping(value = "/getByPersonalNr/{nr}")
    public ResponseEntity<Object> getByPersonalNr(@PathVariable Long nr) {
        try {
            return ResponseEntity.ok(userService.getByPersonalNr(nr));
        } catch (UserException e) {
           return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
