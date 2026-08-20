package com.tracking.backend.dto.food;

import com.tracking.backend.entity.enums.FoodType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FoodRequestDTO(

        @NotNull
        FoodType foodType,

        @NotBlank
        @Size(max = 100)
        String description
) {
}
