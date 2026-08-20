package com.tracking.backend.dto.exercise;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ExerciseRequestDTO(

        @NotBlank
        @Size(max = 100)
        String activity,

        @NotBlank
        @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d$", message = "totalTime must be in HH:MM format")
        String totalTime
) {
}
