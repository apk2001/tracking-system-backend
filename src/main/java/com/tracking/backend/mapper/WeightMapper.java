package com.tracking.backend.mapper;

import com.tracking.backend.dto.weight.WeightRequestDTO;
import com.tracking.backend.dto.weight.WeightResponseDTO;
import com.tracking.backend.entity.WeightLog;
import org.springframework.stereotype.Component;

@Component
public class WeightMapper implements EntityMapper<WeightLog, WeightRequestDTO, WeightResponseDTO> {

    @Override
    public WeightLog toEntity(WeightRequestDTO request) {
        WeightLog entity = new WeightLog();
        entity.setWeightKg(request.weightKg());
        return entity;
    }

    @Override
    public WeightResponseDTO toResponse(WeightLog entity) {
        return new WeightResponseDTO(
                entity.getId(),
                entity.getCreatedAt().format(DateTimeFormats.DATE),
                entity.getWeightKg());
    }
}
