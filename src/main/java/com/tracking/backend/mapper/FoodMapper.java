package com.tracking.backend.mapper;

import com.tracking.backend.dto.food.FoodRequestDTO;
import com.tracking.backend.dto.food.FoodResponseDTO;
import com.tracking.backend.entity.FoodLog;
import org.springframework.stereotype.Component;

@Component
public class FoodMapper implements EntityMapper<FoodLog, FoodRequestDTO, FoodResponseDTO> {

    @Override
    public FoodLog toEntity(FoodRequestDTO request) {
        FoodLog entity = new FoodLog();
        entity.setFoodType(request.foodType());
        entity.setDescription(request.description());
        return entity;
    }

    @Override
    public FoodResponseDTO toResponse(FoodLog entity) {
        return new FoodResponseDTO(
                entity.getId(),
                entity.getCreatedAt().format(DateTimeFormats.DATE),
                entity.getCreatedAt().format(DateTimeFormats.TIME),
                entity.getFoodType(),
                entity.getDescription());
    }
}
