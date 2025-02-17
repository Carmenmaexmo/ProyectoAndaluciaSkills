package com.example.andaluciaskills.controller;

import com.example.andaluciaskills.dto.UserLoginDTO;
import com.example.andaluciaskills.dto.UserRegisterDTO;
import com.example.andaluciaskills.service.UserService;
import com.example.andaluciaskills.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody UserRegisterDTO userRegisterDTO) {
        userService.agregarUser(userRegisterDTO);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDTO userLoginDTO) throws Exception {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Invalid username or password");
        }
        final UserDetails userDetails = userService.loadUserByUsername(userLoginDTO.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return jwt;
    }
}