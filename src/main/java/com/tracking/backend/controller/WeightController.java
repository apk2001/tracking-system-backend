package com.tracking.backend.controller;

import com.tracking.backend.dto.weight.WeightRequestDTO;
import com.tracking.backend.dto.weight.WeightResponseDTO;
import com.tracking.backend.service.TrackingService;
import com.tracking.backend.service.WeightService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weight")
public class WeightController extends AbstractTrackingController<WeightRequestDTO, WeightResponseDTO> {

    private final WeightService weightService;

    public WeightController(WeightService weightService) {
        this.weightService = weightService;
    }

    @Override
    protected TrackingService<WeightRequestDTO, WeightResponseDTO> service() {
        return weightService;
    }
}
