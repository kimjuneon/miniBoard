package com.example.miniboard.post.dto;

import com.example.miniboard.post.domain.Post;

import java.time.LocalDateTime;

public record PostResponse(
        Long id,
        String title,
        String content,
        String author,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static PostResponse from(Post p) {
        return new PostResponse(
                p.getId(),
                p.getTitle(),
                p.getContent(),
                p.getAuthor(),
                p.getCreatedAt(),
                p.getUpdatedAt()
        );
    }
}

