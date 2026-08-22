package com.tracking.backend.dto.food;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tracking.backend.entity.enums.FoodType;
import com.tracking.backend.mapper.DateTimeFormats;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record FoodRequestDTO(

        @NotNull
        FoodType foodType,

        @NotBlank
        @Size(max = 100)
        String description,

        @NotNull
        @JsonFormat(pattern = DateTimeFormats.DATETIME_PATTERN)
        LocalDateTime eatenAt
) {
}
