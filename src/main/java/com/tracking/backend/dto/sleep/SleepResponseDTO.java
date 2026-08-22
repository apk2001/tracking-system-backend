package com.tracking.backend.dto.sleep;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tracking.backend.entity.enums.SleepQuality;
import com.tracking.backend.mapper.DateTimeFormats;

import java.time.LocalDateTime;

public record SleepResponseDTO(
        Long id,

        @JsonFormat(pattern = DateTimeFormats.DATETIME_PATTERN)
        LocalDateTime bedTime,

        @JsonFormat(pattern = DateTimeFormats.DATETIME_PATTERN)
        LocalDateTime wakeTime,

        String totalTime,
        SleepQuality quality
) {
}
