package com.tracking.backend.dto.thought;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ThoughtRequestDTO(

        @NotBlank
        @Size(max = 1500)
        String content
) {
}
