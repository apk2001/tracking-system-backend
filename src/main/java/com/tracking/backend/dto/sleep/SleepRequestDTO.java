package com.tracking.backend.dto.sleep;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tracking.backend.entity.enums.SleepQuality;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record SleepRequestDTO(

        @NotNull
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime bedTime,

        @NotNull
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime wakeTime,

        @NotNull
        SleepQuality quality
) {
}
