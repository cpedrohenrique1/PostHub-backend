package edu.posthub.posthub.dtos;

import java.time.Instant;

public record FeedItemDTO(Long postId, String content, String username, Instant creationTimestamp) {

}
