package com.tracking.backend.controller;

import com.tracking.backend.dto.exercise.ExerciseRequestDTO;
import com.tracking.backend.dto.exercise.ExerciseResponseDTO;
import com.tracking.backend.service.ExerciseService;
import com.tracking.backend.service.TrackingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exercise")
public class ExerciseController extends AbstractTrackingController<ExerciseRequestDTO, ExerciseResponseDTO> {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @Override
    protected TrackingService<ExerciseRequestDTO, ExerciseResponseDTO> service() {
        return exerciseService;
    }
}
