package com.telran.service.jwt;

import org.springframework.security.core.Authentication;

import java.util.Map;

public interface JwtService {
    String generatedJwtToken(String subject, Map<String,Object> claims);
    Authentication tokenToAuthentication(String token);
}
