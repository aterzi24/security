package com.example.security.dto;

import com.example.security.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRequestDto {
    private String username;
    private String password;
    private Role role;
}
