package com.tracking.backend.mapper;

import com.tracking.backend.config.RequestTimeZoneHolder;
import com.tracking.backend.dto.emotion.EmotionRequestDTO;
import com.tracking.backend.dto.emotion.EmotionResponseDTO;
import com.tracking.backend.entity.EmotionLog;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class EmotionMapper implements EntityMapper<EmotionLog, EmotionRequestDTO, EmotionResponseDTO> {

    private final RequestTimeZoneHolder requestTimeZoneHolder;

    public EmotionMapper(RequestTimeZoneHolder requestTimeZoneHolder) {
        this.requestTimeZoneHolder = requestTimeZoneHolder;
    }

    @Override
    public EmotionLog toEntity(EmotionRequestDTO request) {
        EmotionLog entity = new EmotionLog();
        entity.setFeeling(request.feeling());
        entity.setReason(request.reason());
        return entity;
    }

    @Override
    public EmotionResponseDTO toResponse(EmotionLog entity) {
        ZonedDateTime createdAt = entity.getCreatedAt().atZone(requestTimeZoneHolder.getZoneId());
        return new EmotionResponseDTO(
                entity.getId(),
                createdAt.format(DateTimeFormats.DATE),
                createdAt.format(DateTimeFormats.TIME),
                entity.getFeeling(),
                entity.getReason());
    }
}
