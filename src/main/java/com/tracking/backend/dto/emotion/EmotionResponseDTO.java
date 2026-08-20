package com.tracking.backend.dto.emotion;

public record EmotionResponseDTO(
        Long id,
        String date,
        String time,
        String feeling,
        String reason
) {
}
