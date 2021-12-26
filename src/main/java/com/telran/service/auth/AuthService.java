package com.telran.service.auth;

import com.telran.document.UserDoc;
import com.telran.dto.AuthDto;
import org.springframework.stereotype.Service;

public interface AuthService {
    boolean addUser(AuthDto user);
    UserDoc getUserByUsername(String username);
}
