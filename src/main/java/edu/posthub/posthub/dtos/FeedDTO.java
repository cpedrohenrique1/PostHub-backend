package edu.posthub.posthub.dtos;

import java.util.List;

public record FeedDTO(
    List<FeedItemDTO> feedItems, 
    int page, 
    int pageSize,
    int totalPages,
    Long totalElements
    ) {
}
