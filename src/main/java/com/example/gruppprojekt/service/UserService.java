package com.example.gruppprojekt.service;

import com.example.gruppprojekt.model.Users;
import com.example.gruppprojekt.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-05-18
 * Time:  10:44
 * Project: ALM-grupp-projekt
 * Copyright: MIT
 */
@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public Users addUser(Users user) {
        return repository.save(user);
    }

    public List<Users> getAllUsers() {
        return repository.findAll();
    }

    public String deleteUserById(String id) {
        repository.deleteById(id);
        return "User with id: " + id + " was deleted!";
    }

    public Users updateUser(Users user) {
        return repository.save(user);
    }
}
