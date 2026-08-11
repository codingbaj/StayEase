package com.codder.stayease.controller;

import com.codder.stayease.dto.LoginRequest;
import com.codder.stayease.dto.LoginResponse;
import com.codder.stayease.response.ApiResponse;
import com.codder.stayease.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public ApiResponse login(
            @RequestBody LoginRequest request) {

        LoginResponse response =
                authService.login(request);

        return new ApiResponse(
                true,
                "Login Successfully!",
                response
        );
    }
}