package com.eks.productivitybackend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{userSubject}")
    @PreAuthorize("#auth.token.claims['sub'] == #userSubject")
    public String getUserById(@PathVariable("userSubject") String userSubject, JwtAuthenticationToken auth) {
        return "yeah_baby";
    }

    @GetMapping("/all")
    public String getAllUsers() {
        return "all";
    }
}
