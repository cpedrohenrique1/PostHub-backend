package edu.posthub.posthub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import edu.posthub.posthub.dtos.LoginRequestDTO;
import edu.posthub.posthub.dtos.LoginResponseDTO;
import edu.posthub.posthub.services.TokenService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        LoginResponseDTO response = tokenService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}
