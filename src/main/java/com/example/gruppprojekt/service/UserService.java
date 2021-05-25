package com.example.gruppprojekt.service;

import com.example.gruppprojekt.model.Book;
import com.example.gruppprojekt.model.Users;
import com.example.gruppprojekt.repo.UserRepository;
import com.example.gruppprojekt.util.Encrypt;
import com.example.gruppprojekt.util.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    /**
     * Save a user to DB
     * @param user Users.java
     * @return Users.java
     */
    public Users addUser(Users user) throws UserException {
        Users u = repository.findUsersByEmailOrPersonalNumber(user.getEmail(), user.getPersonalNumber());
        if (u != null) {
            throw new UserException("Either email or social security number already exists or you are our user, " +
                    "\nplease change one of them and try again or log in ");
        }
        return repository.save(user);
    }

    /**
     * Get all users
     * @return List of users
     */
    public List<Users> getAllUsers() {
        return repository.findAll();
    }

    /**
     * Delete a user by id
     * @param id String user id
     * @return String message in the response body
     */
    public String deleteUserById(String id) throws UserException {
        Optional<Users> u = repository.findById(id);
        if (u.isPresent()) {
            Users existingUser = u.get();
            repository.deleteById(id);
            return "User deleted with social security number: " + existingUser.getPersonalNumber() +
                    "\n and named: " + existingUser.getFirstName() + ", " + existingUser.getLastName();
        }
       throw new UserException("There is no user with this id number: " +id);
    }

    /**
     * Update user information(User can not change their personal number)
     * @param user Users.java
     * @return User entity
     * @throws UserException will be a message in response body
     */
    public Users updateUserProfile(Users user) throws UserException {
        Optional<Users> u = repository.findById(user.getId());

        if (u.isPresent()) {
            Users existingUser = u.get();
            if (!user.getPersonalNumber().equals(existingUser.getPersonalNumber()))
                throw new UserException("You can't change your social security number\n* Must be the same as before *");
            if (user.getPassword()!=null && !Encrypt.getMd5(user.getPassword()).equals(existingUser.getPassword()))
                existingUser.setPassword(Encrypt.getMd5(user.getPassword()));
            existingUser.setEmail(user.getEmail());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            return repository.save(existingUser);
        }
        throw new UserException("Something went wrong please try again later");
    }

    /**
     * Add,remove book for user
     * @param userID String user ID
     * @param books List<Book.java>
     * @return user entity
     * @throws UserException will be a message in response body
     */
    public Users updateUserBooks(String userID, List<Book> books) throws UserException {
        Optional<Users> u = repository.findById(userID);

        if (u.isPresent()) {
            Users existingUser = u.get();
            if (books.isEmpty()) {
                existingUser.setBooks(new ArrayList<>()); // för att tomma book list
            } else {
                existingUser.setBooks(books);
            }
            return repository.save(existingUser);
        }
        throw new UserException("Something went wrong please try again later");
    }

    /**
     * If the method finds the person then sends the person to the control class else throws an exception
     *
     * @param email String users E-post
     * @param personalNR Long personal number
     * @param psw String password
     * @return an object of user
     * @throws UserException will be a message in response body
     */
    public Users UserAuthentication(String email, Long personalNR, String psw) throws UserException {
        Users u = repository.findUsersByEmailAndPasswordOrPersonalNumberAndPassword(email,psw, personalNR, psw);
        if (u != null) {
            return u;
        } else {
            throw new UserException("Invalid email/personal number or password");
        }
    }

    /**
     * Get user by personal number
     * @param nr Long personal number
     * @return user entity
     * @throws UserException will be a message in response body
     */
    public Users getByPersonalNr(Long nr) throws UserException {
        Users u = repository.findUsersByPersonalNumber(nr);
        if (u != null) return u;
        throw new UserException("Something went wrong please try again later\n" +
                "Check your input");
    }
}
