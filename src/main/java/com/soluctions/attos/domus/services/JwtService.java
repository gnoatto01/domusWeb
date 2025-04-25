package com.soluctions.attos.domus.services;


import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

import com.soluctions.attos.domus.dtos.LoginRequest;
import com.soluctions.attos.domus.entities.Role;
import com.soluctions.attos.domus.repositories.UserRepository;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class JwtService {

    private final JwtEncoder encoder;
    private final JwtDecoder decoder;
    private final UserRepository userRepository;

 

    public String generateToken(LoginRequest loginRequest) {
        var user = userRepository.findByUsername(loginRequest.username());
        Instant now = Instant.now();
        long expiresIn = 86400L;

        var scopes = user.get().getRoles() // pega a role do usuario
                .stream()
                .map(Role::getRoleName)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("attos-soluctions")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .subject(user.get().getId().toString())
                .claim("scope", scopes)
                .build();

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public boolean validateToken(String accessToken) {
        try {
            Instant now = Instant.now();
            Instant expirationDate;

            Jwt decodedJwt = decoder.decode(accessToken);
            expirationDate = decodedJwt.getExpiresAt();

            if (expirationDate != null && expirationDate.isAfter(now)) {
                return true;
            } else {
                return false;
            }

        } catch (JwtException e) {
            return false;
        }
    }

    

}