package com.tracking.backend.mapper;

import com.tracking.backend.config.RequestTimeZoneHolder;
import com.tracking.backend.dto.food.FoodRequestDTO;
import com.tracking.backend.dto.food.FoodResponseDTO;
import com.tracking.backend.entity.FoodLog;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class FoodMapper implements EntityMapper<FoodLog, FoodRequestDTO, FoodResponseDTO> {

    private final RequestTimeZoneHolder requestTimeZoneHolder;

    public FoodMapper(RequestTimeZoneHolder requestTimeZoneHolder) {
        this.requestTimeZoneHolder = requestTimeZoneHolder;
    }

    @Override
    public FoodLog toEntity(FoodRequestDTO request) {
        FoodLog entity = new FoodLog();
        entity.setFoodType(request.foodType());
        entity.setDescription(request.description());
        entity.setEatenAt(request.eatenAt().atZone(requestTimeZoneHolder.getZoneId()).toInstant());
        return entity;
    }

    @Override
    public FoodResponseDTO toResponse(FoodLog entity) {
        ZonedDateTime eatenAt = entity.getEatenAt().atZone(requestTimeZoneHolder.getZoneId());
        return new FoodResponseDTO(
                entity.getId(),
                eatenAt.format(DateTimeFormats.DATE),
                eatenAt.format(DateTimeFormats.TIME),
                entity.getFoodType(),
                entity.getDescription());
    }
}
