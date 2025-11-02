package edu.posthub.posthub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.posthub.posthub.dtos.FeedItemDTO;
import edu.posthub.posthub.repositories.PostRepository;

@Service
public class FeedService {
    @Autowired
    private PostRepository postRepository;

    public Page<FeedItemDTO> feed(int page, int pageSize){
        return  postRepository.
                findAll(PageRequest.of(page, pageSize, Sort.Direction.DESC, "creationTimestamp"))
                .map(post -> new FeedItemDTO(post.getId(), post.getContent(), post.getAuthor().getUsername()));
    }
}
