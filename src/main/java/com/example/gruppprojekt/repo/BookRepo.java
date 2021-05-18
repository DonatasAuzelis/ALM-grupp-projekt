package com.example.gruppprojekt.repo;

import com.example.gruppprojekt.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends MongoRepository<Book, String> {

    List<Book> findBooksByAuthor(String author);
    List<Book> findBooksByTitle(String title);

}
