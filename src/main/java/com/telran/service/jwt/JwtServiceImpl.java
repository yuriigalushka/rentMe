package com.telran.service.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JwtServiceImpl implements JwtService {
    private final String SECRET_KEY = "175a6df7c66849ddbb775cbc01f91560175a6df7c66849ddbb775cbc01f91560";
    private final long TOKEN_TTL = 600000;
    private final SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
    private SecretKey key;

    @PostConstruct
    void init(){
        key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String generatedJwtToken(String subject, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)))
                .setSubject(subject)
                .setIssuer("TelRan")
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_TTL))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(key, algorithm)
                .compact();
    }

    @Override
    public Authentication tokenToAuthentication(String token) {//Bearer jwthash
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token.replace("Bearer ",""))
                .getBody();

        String publicId = claims.getSubject();
        List<?> roles = claims.get("roles",List.class);
        if(publicId == null || roles == null){
            return null;
        }
        return new UsernamePasswordAuthenticationToken(
                publicId,
                null,
                AuthorityUtils.createAuthorityList(roles.toArray(String[]::new))
        );
    }
}
