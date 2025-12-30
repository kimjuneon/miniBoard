package com.example.miniboard.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostUpdateRequest(
        @NotBlank @Size(max = 100) String title,
        @NotBlank String content
) {}
