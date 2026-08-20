package com.tracking.backend.controller;

import com.tracking.backend.dto.emotion.EmotionRequestDTO;
import com.tracking.backend.dto.emotion.EmotionResponseDTO;
import com.tracking.backend.service.EmotionService;
import com.tracking.backend.service.TrackingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/emotions")
public class EmotionController extends AbstractTrackingController<EmotionRequestDTO, EmotionResponseDTO> {

    private final EmotionService emotionService;

    public EmotionController(EmotionService emotionService) {
        this.emotionService = emotionService;
    }

    @Override
    protected TrackingService<EmotionRequestDTO, EmotionResponseDTO> service() {
        return emotionService;
    }
}
