package com.example.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class DemoController {
    @GetMapping("demo/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String demoAdmin() {
        return "demoAdmin";
    }

    @GetMapping("demo/user")
    @PreAuthorize("hasAnyAuthority('USER')")
    public String demoUser() {
        return "demoUser";
    }
}
