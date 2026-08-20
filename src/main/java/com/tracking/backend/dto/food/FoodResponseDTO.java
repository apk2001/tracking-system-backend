package com.tracking.backend.dto.food;

import com.tracking.backend.entity.enums.FoodType;

public record FoodResponseDTO(
        Long id,
        String date,
        String time,
        FoodType foodType,
        String description
) {
}
