package com.example.gruppprojekt.repo;

import com.example.gruppprojekt.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends MongoRepository<Category,String> {

    Optional<Category> deleteCategoryByName(String name);
    Optional<Category> findCategoryByName(String name);
}
