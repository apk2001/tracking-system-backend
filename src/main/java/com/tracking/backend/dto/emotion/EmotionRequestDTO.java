package com.tracking.backend.dto.emotion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmotionRequestDTO(

        @NotBlank
        @Size(max = 100)
        String feeling,

        @NotBlank
        @Size(max = 500)
        String reason
) {
}
