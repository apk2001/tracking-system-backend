package com.tracking.backend.mapper;

import com.tracking.backend.config.RequestTimeZoneHolder;
import com.tracking.backend.dto.thought.ThoughtRequestDTO;
import com.tracking.backend.dto.thought.ThoughtResponseDTO;
import com.tracking.backend.entity.ThoughtLog;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class ThoughtMapper implements EntityMapper<ThoughtLog, ThoughtRequestDTO, ThoughtResponseDTO> {

    private final RequestTimeZoneHolder requestTimeZoneHolder;

    public ThoughtMapper(RequestTimeZoneHolder requestTimeZoneHolder) {
        this.requestTimeZoneHolder = requestTimeZoneHolder;
    }

    @Override
    public ThoughtLog toEntity(ThoughtRequestDTO request) {
        ThoughtLog entity = new ThoughtLog();
        entity.setContent(request.content());
        return entity;
    }

    @Override
    public ThoughtResponseDTO toResponse(ThoughtLog entity) {
        ZonedDateTime createdAt = entity.getCreatedAt().atZone(requestTimeZoneHolder.getZoneId());
        return new ThoughtResponseDTO(
                entity.getId(),
                createdAt.format(DateTimeFormats.DATE),
                createdAt.format(DateTimeFormats.TIME),
                entity.getContent());
    }
}
