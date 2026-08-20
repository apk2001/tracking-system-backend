package com.tracking.backend.controller;

import com.tracking.backend.dto.thought.ThoughtRequestDTO;
import com.tracking.backend.dto.thought.ThoughtResponseDTO;
import com.tracking.backend.service.ThoughtService;
import com.tracking.backend.service.TrackingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/thoughts")
public class ThoughtController extends AbstractTrackingController<ThoughtRequestDTO, ThoughtResponseDTO> {

    private final ThoughtService thoughtService;

    public ThoughtController(ThoughtService thoughtService) {
        this.thoughtService = thoughtService;
    }

    @Override
    protected TrackingService<ThoughtRequestDTO, ThoughtResponseDTO> service() {
        return thoughtService;
    }
}
