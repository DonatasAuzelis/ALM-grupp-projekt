package com.example.gruppprojekt.repo;

import com.example.gruppprojekt.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ashkan Amiri
 * Date:  2021-05-18
 * Time:  10:44
 * Project: ALM-grupp-projekt
 * Copyright: MIT
 */
@Repository
public interface UserRepository extends MongoRepository<Users,String> {
}
