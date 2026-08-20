package com.tracking.backend.controller;

import com.tracking.backend.dto.sleep.SleepRequestDTO;
import com.tracking.backend.dto.sleep.SleepResponseDTO;
import com.tracking.backend.service.SleepService;
import com.tracking.backend.service.TrackingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sleep")
public class SleepController extends AbstractTrackingController<SleepRequestDTO, SleepResponseDTO> {

    private final SleepService sleepService;

    public SleepController(SleepService sleepService) {
        this.sleepService = sleepService;
    }

    @Override
    protected TrackingService<SleepRequestDTO, SleepResponseDTO> service() {
        return sleepService;
    }
}
