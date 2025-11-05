package edu.posthub.posthub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import edu.posthub.posthub.configs.SecurityConfig;
import edu.posthub.posthub.dtos.CreateUserDTO;
import edu.posthub.posthub.entities.User;
import edu.posthub.posthub.repositories.UserRepository;
import edu.posthub.posthub.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/users")
@Tag(name = "users", description = "cria usuarios e retorna")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping()
    @Operation(summary = "cria usuario", description = "a partir de um body")
    @ApiResponse(responseCode = "200", description = "void")
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDTO createUserDTO) {
        userService.createUser(createUserDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @Operation(summary = "get usuario", description = "retorna todos")
    @ApiResponse(responseCode = "200", description = "lista de usuarios")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
