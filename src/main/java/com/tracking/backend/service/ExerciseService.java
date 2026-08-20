package com.tracking.backend.service;

import com.tracking.backend.dto.exercise.ExerciseRequestDTO;
import com.tracking.backend.dto.exercise.ExerciseResponseDTO;
import com.tracking.backend.entity.ExerciseLog;
import com.tracking.backend.mapper.ExerciseMapper;
import com.tracking.backend.repository.ExerciseLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService extends AbstractTrackingService<ExerciseLog, ExerciseRequestDTO, ExerciseResponseDTO> {

    private final ExerciseLogRepository exerciseLogRepository;

    public ExerciseService(ExerciseLogRepository exerciseLogRepository, ExerciseMapper exerciseMapper) {
        super(exerciseLogRepository, exerciseMapper);
        this.exerciseLogRepository = exerciseLogRepository;
    }

    @Override
    protected List<ExerciseLog> fetchRecent() {
        return exerciseLogRepository.findTop10ByOrderByCreatedAtDesc();
    }
}
