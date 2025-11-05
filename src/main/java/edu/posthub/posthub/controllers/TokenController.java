package edu.posthub.posthub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import edu.posthub.posthub.configs.SecurityConfig;
import edu.posthub.posthub.dtos.LoginRequestDTO;
import edu.posthub.posthub.dtos.LoginResponseDTO;
import edu.posthub.posthub.services.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@Tag(name = "Token controller", description = "autenticacao")
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    @Operation(summary = "Retorna o token para usuario ja cadastrado")
    @ApiResponse(responseCode = "200", description = "Recebe um body")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        LoginResponseDTO response = tokenService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}
