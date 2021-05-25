package com.example.gruppprojekt.repo;

import com.example.gruppprojekt.model.Author;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepo extends MongoRepository<Author, String> {
    List<Author> findAllByOrderByLastModifiedDateDesc();

}
