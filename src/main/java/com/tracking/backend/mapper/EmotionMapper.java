package com.tracking.backend.mapper;

import com.tracking.backend.dto.emotion.EmotionRequestDTO;
import com.tracking.backend.dto.emotion.EmotionResponseDTO;
import com.tracking.backend.entity.EmotionLog;
import org.springframework.stereotype.Component;

@Component
public class EmotionMapper implements EntityMapper<EmotionLog, EmotionRequestDTO, EmotionResponseDTO> {

    @Override
    public EmotionLog toEntity(EmotionRequestDTO request) {
        EmotionLog entity = new EmotionLog();
        entity.setFeeling(request.feeling());
        entity.setReason(request.reason());
        return entity;
    }

    @Override
    public EmotionResponseDTO toResponse(EmotionLog entity) {
        return new EmotionResponseDTO(
                entity.getId(),
                entity.getCreatedAt().format(DateTimeFormats.DATE),
                entity.getCreatedAt().format(DateTimeFormats.TIME),
                entity.getFeeling(),
                entity.getReason());
    }
}
