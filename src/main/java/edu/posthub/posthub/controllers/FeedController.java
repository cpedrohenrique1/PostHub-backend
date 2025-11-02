package edu.posthub.posthub.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.posthub.posthub.dtos.FeedDTO;
import edu.posthub.posthub.dtos.FeedItemDTO;
import edu.posthub.posthub.services.FeedService;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/feed")
public class FeedController {
    @Autowired
    private FeedService feedService;

    @GetMapping()
    public ResponseEntity<FeedDTO> feed(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Page<FeedItemDTO> feedItems = feedService.feed(page, pageSize);
        return ResponseEntity.ok(new FeedDTO(feedItems.getContent(), page, pageSize, feedItems.getTotalPages(), feedItems.getTotalElements()));
    }

}
