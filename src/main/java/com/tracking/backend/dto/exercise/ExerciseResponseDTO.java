package com.tracking.backend.dto.exercise;

public record ExerciseResponseDTO(
        Long id,
        String date,
        String time,
        String activity,
        String totalTime
) {
}
