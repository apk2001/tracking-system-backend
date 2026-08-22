package com.tracking.backend.mapper;

import com.tracking.backend.config.RequestTimeZoneHolder;
import com.tracking.backend.dto.exercise.ExerciseRequestDTO;
import com.tracking.backend.dto.exercise.ExerciseResponseDTO;
import com.tracking.backend.entity.ExerciseLog;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class ExerciseMapper implements EntityMapper<ExerciseLog, ExerciseRequestDTO, ExerciseResponseDTO> {

    private final RequestTimeZoneHolder requestTimeZoneHolder;

    public ExerciseMapper(RequestTimeZoneHolder requestTimeZoneHolder) {
        this.requestTimeZoneHolder = requestTimeZoneHolder;
    }

    @Override
    public ExerciseLog toEntity(ExerciseRequestDTO request) {
        ExerciseLog entity = new ExerciseLog();
        entity.setActivity(request.activity());
        entity.setTotalTime(request.totalTime());
        return entity;
    }

    @Override
    public ExerciseResponseDTO toResponse(ExerciseLog entity) {
        ZonedDateTime createdAt = entity.getCreatedAt().atZone(requestTimeZoneHolder.getZoneId());
        return new ExerciseResponseDTO(
                entity.getId(),
                createdAt.format(DateTimeFormats.DATE),
                createdAt.format(DateTimeFormats.TIME),
                entity.getActivity(),
                entity.getTotalTime());
    }
}
