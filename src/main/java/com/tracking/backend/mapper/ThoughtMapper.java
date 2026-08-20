package com.tracking.backend.mapper;

import com.tracking.backend.dto.thought.ThoughtRequestDTO;
import com.tracking.backend.dto.thought.ThoughtResponseDTO;
import com.tracking.backend.entity.ThoughtLog;
import org.springframework.stereotype.Component;

@Component
public class ThoughtMapper implements EntityMapper<ThoughtLog, ThoughtRequestDTO, ThoughtResponseDTO> {

    @Override
    public ThoughtLog toEntity(ThoughtRequestDTO request) {
        ThoughtLog entity = new ThoughtLog();
        entity.setContent(request.content());
        return entity;
    }

    @Override
    public ThoughtResponseDTO toResponse(ThoughtLog entity) {
        return new ThoughtResponseDTO(
                entity.getId(),
                entity.getCreatedAt().format(DateTimeFormats.DATE),
                entity.getCreatedAt().format(DateTimeFormats.TIME),
                entity.getContent());
    }
}
