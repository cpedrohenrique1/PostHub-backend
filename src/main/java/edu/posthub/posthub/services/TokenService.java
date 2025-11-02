package edu.posthub.posthub.services;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import edu.posthub.posthub.dtos.LoginRequestDTO;
import edu.posthub.posthub.dtos.LoginResponseDTO;
import edu.posthub.posthub.entities.User;
import edu.posthub.posthub.repositories.UserRepository;

@Service
public class TokenService {
    @Autowired
    private JwtEncoder jwtEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        Optional<User> user = userRepository.findByUsername(loginRequest.username());

        if (user.isEmpty() || !user.get().isLoginCorrect(loginRequest, passwordEncoder)){
            throw new BadCredentialsException("user or password is invalid");
        }

        Instant now = Instant.now();
        Long expiresIn = 300L;

        var jwtClaims = JwtClaimsSet
            .builder()
            .issuer("mybackend")
            .subject(user.get().getId().toString())
            .issuedAt(now)
            .expiresAt(now.plusSeconds(expiresIn))
            .build();

        String jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaims)).getTokenValue();

        return new LoginResponseDTO(jwtValue, expiresIn);
    }
}
