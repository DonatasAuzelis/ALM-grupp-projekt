package com.example.gruppprojekt.repo;

import com.example.gruppprojekt.model.Book;
import com.example.gruppprojekt.model.Users;
import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Ashkan Amiri
 * Date:  2021-05-18
 * Time:  10:44
 * Project: ALM-grupp-projekt
 * Copyright: MIT
 */
@Repository
public interface UserRepository extends MongoRepository<Users,String> {
   Users findUsersByPersonalNumber(Long personalNumber);
   Users findUsersByEmailOrPersonalNumber(String email,Long personalNumber);
   Users findUsersByEmailAndPasswordOrPersonalNumberAndPassword(String email,String psw1, Long personalNR, String psw);
}
