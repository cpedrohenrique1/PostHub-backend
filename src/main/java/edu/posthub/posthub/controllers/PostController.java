package edu.posthub.posthub.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.posthub.posthub.configs.SecurityConfig;
import edu.posthub.posthub.dtos.CreatePostDTO;
import edu.posthub.posthub.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/posts")
@Tag(name = "Posts", description = "Controller para posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping()
    @Operation(summary = "cria posts", description = "Cria post a partir de um body")
    @ApiResponse(responseCode = "200", description = "void")
    public ResponseEntity<Void> createPost (@RequestBody CreatePostDTO createPostDTO, 
        JwtAuthenticationToken jwtAuthenticationToken) {

        postService.createPost(createPostDTO, jwtAuthenticationToken);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "deleta posts", description = "deleta post a partir de um parametro e token")
    @ApiResponse(responseCode = "200", description = "void")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long id, JwtAuthenticationToken jwtAuthenticationToken){
        postService.deletePost(id, jwtAuthenticationToken);

        return ResponseEntity.ok().build();
    }
}
