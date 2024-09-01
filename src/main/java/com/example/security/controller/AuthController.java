package com.example.security.controller;

import com.example.security.dto.AuthRequestDto;
import com.example.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("register")
    public void register(@RequestBody AuthRequestDto request) {
        authService.register(request);
    }

    @PostMapping("authenticate")
    public String authenticate(@RequestBody AuthRequestDto request) {
        return authService.authenticate(request);
    }
}
