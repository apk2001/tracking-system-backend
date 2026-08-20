package com.tracking.backend.dto.sleep;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tracking.backend.entity.enums.SleepQuality;

import java.time.LocalDateTime;

public record SleepResponseDTO(
        Long id,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime bedTime,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime wakeTime,

        String totalTime,
        SleepQuality quality
) {
}
