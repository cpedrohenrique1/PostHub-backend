package edu.posthub.posthub.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import edu.posthub.posthub.dtos.CreatePostDTO;
import edu.posthub.posthub.entities.Post;
import edu.posthub.posthub.entities.User;
import edu.posthub.posthub.repositories.PostRepository;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;

    public void createPost(CreatePostDTO createPostDTO, JwtAuthenticationToken jwtAuthenticationToken){
        User user = userService.getUserById(UUID.fromString(jwtAuthenticationToken.getName()));

        Post post = new Post();
        post.setAuthor(user);
        post.setContent(createPostDTO.content());

        postRepository.save(post);
    }

    public void deletePost(Long id, JwtAuthenticationToken jwtAuthenticationToken){
        Post post = postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        
        if (!post.getAuthor().getId().equals(UUID.fromString(jwtAuthenticationToken.getName()))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        postRepository.deleteById(id);
    }
}
