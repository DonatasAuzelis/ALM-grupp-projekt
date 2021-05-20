package com.example.gruppprojekt.repo;

import com.example.gruppprojekt.model.Author;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends MongoRepository<Author, String> {


}
