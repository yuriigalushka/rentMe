package com.telran.dao.auth;

import com.telran.document.UserDoc;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMongoRepository extends MongoRepository<UserDoc, ObjectId>{
    UserDoc findByUsername(String username);
    UserDoc save(UserDoc userDoc);
}