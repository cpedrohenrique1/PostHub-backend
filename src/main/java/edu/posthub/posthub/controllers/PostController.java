package edu.posthub.posthub.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.posthub.posthub.dtos.CreatePostDTO;
import edu.posthub.posthub.services.PostService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping()
    public ResponseEntity<Void> createPost (@RequestBody CreatePostDTO createPostDTO, 
        JwtAuthenticationToken jwtAuthenticationToken) {

        postService.createPost(createPostDTO, jwtAuthenticationToken);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long id, JwtAuthenticationToken jwtAuthenticationToken){
        postService.deletePost(id, jwtAuthenticationToken);

        return ResponseEntity.ok().build();
    }
}
