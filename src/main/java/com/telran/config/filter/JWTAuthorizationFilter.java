package com.telran.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telran.service.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    @Autowired
    JwtService jwtService;
    @Autowired
    ObjectMapper mapper;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        if(request.getRequestURI().startsWith("/auth")){
            chain.doFilter(request,response);
            return;
        }

        final String requestHeader = request.getHeader("Authorization");

        if (requestHeader == null || !requestHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        Authentication authentication = jwtService.tokenToAuthentication(requestHeader);

        if(authentication != null){
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request,response);
    }
}