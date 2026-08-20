package com.tracking.backend.dto.thought;

public record ThoughtResponseDTO(
        Long id,
        String date,
        String time,
        String content
) {
}
