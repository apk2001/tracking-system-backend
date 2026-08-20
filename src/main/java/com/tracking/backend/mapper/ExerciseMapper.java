package com.tracking.backend.mapper;

import com.tracking.backend.dto.exercise.ExerciseRequestDTO;
import com.tracking.backend.dto.exercise.ExerciseResponseDTO;
import com.tracking.backend.entity.ExerciseLog;
import org.springframework.stereotype.Component;

@Component
public class ExerciseMapper implements EntityMapper<ExerciseLog, ExerciseRequestDTO, ExerciseResponseDTO> {

    @Override
    public ExerciseLog toEntity(ExerciseRequestDTO request) {
        ExerciseLog entity = new ExerciseLog();
        entity.setActivity(request.activity());
        entity.setTotalTime(request.totalTime());
        return entity;
    }

    @Override
    public ExerciseResponseDTO toResponse(ExerciseLog entity) {
        return new ExerciseResponseDTO(
                entity.getId(),
                entity.getCreatedAt().format(DateTimeFormats.DATE),
                entity.getCreatedAt().format(DateTimeFormats.TIME),
                entity.getActivity(),
                entity.getTotalTime());
    }
}
