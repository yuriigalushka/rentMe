package com.telran.controller.auth;

import com.telran.dto.AuthDto;
import com.telran.dto.AuthResponseDto;
import com.telran.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
@Validated
public class AuthController {
    @Autowired
    AuthService service;

    @PostMapping("registration")
    public AuthResponseDto registration(@RequestBody @Valid AuthDto dto) {
        if (service.addUser(dto)) {
            return AuthResponseDto.builder().message("Registration successfully").build();
        }
        return AuthResponseDto.builder().message("Registration unsuccessfully").build();
    }
}
