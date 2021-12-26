package com.telran.service.auth;

import com.telran.dao.auth.UserMongoRepository;
import com.telran.document.UserDoc;
import com.telran.dto.AuthDto;
import com.telran.exception.UserDuplicateException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    UserMongoRepository repo;
    @Autowired
    PasswordEncoder encoder;

    @Override
    public boolean addUser(AuthDto user) {
        if (repo.findByUsername(user.getUsername()) != null) {
            throw new UserDuplicateException("User with username: " + user.getUsername() + " already exists");
        }
        repo.save(UserDoc.builder()
                .username(user.getUsername())
                .password(encoder.encode(user.getPassword()))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .photoUrl(user.getPhotoUrl())
                .roles(List.of(user.getRole()))
                .build());
        return true;
    }

    @Override
    public UserDoc getUserByUsername(String username) {
        return repo.findByUsername(username);
    }
}
