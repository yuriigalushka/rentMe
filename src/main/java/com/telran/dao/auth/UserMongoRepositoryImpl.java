package com.telran.dao.auth;

import com.telran.document.UserDoc;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserMongoRepositoryImpl {
    private final MongoTemplate template;

//    @Override
    public UserDoc findByUsername(String username) {
        return template.findById(username, UserDoc.class);
    }
}
