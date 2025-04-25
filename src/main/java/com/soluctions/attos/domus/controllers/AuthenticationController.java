package com.soluctions.attos.domus.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soluctions.attos.domus.dtos.LoginRequest;
import com.soluctions.attos.domus.dtos.LoginResponse;
import com.soluctions.attos.domus.repositories.UserRepository;
import com.soluctions.attos.domus.services.AuthenticationService;

import lombok.AllArgsConstructor;

@RequestMapping("/attos-api")
@RestController
@AllArgsConstructor

public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        var user = userRepository.findByUsername(loginRequest.username());

        if (user.isEmpty() || !user.get().isLoginCorrect(loginRequest, passwordEncoder)) {
            throw new BadCredentialsException("User or password is invalid");
        }

        var jwtValue = authenticationService.authenticate(loginRequest);

        return ResponseEntity.ok(new LoginResponse(jwtValue));
    }
}
