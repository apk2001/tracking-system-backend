package com.tracking.backend.dto.sleep;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tracking.backend.entity.enums.SleepQuality;
import com.tracking.backend.mapper.DateTimeFormats;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record SleepRequestDTO(

        @NotNull
        @JsonFormat(pattern = DateTimeFormats.DATETIME_PATTERN)
        LocalDateTime bedTime,

        @NotNull
        @JsonFormat(pattern = DateTimeFormats.DATETIME_PATTERN)
        LocalDateTime wakeTime,

        @NotNull
        SleepQuality quality
) {
}
