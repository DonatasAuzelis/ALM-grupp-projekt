package com.example.gruppprojekt.repo;

import com.example.gruppprojekt.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends MongoRepository<Category,String> {

    Category deleteByName(String name);
}
