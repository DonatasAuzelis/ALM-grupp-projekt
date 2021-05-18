package com.example.gruppprojekt.controller;

import com.example.gruppprojekt.model.Book;
import com.example.gruppprojekt.model.Users;
import com.example.gruppprojekt.service.BookService;
import com.example.gruppprojekt.service.UserService;
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
@RequestMapping("/Users")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<Users> addUser(@RequestBody Users user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.deleteUserById(id));
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody Users user) {
        return ResponseEntity.ok(userService.updateUser(user));

    }
}
