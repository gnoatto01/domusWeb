package com.soluctions.attos.domus.services;

import org.springframework.stereotype.Service;

import com.soluctions.attos.domus.dtos.LoginRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final JwtService jwtService;

    public String authenticate(LoginRequest loginRequest) {
        return jwtService.generateToken(loginRequest);
    }

    public boolean validateToken(String accessToken) {
        return jwtService.validateToken(accessToken);
    }
}
