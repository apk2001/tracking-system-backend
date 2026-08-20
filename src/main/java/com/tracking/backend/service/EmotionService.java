package com.tracking.backend.service;

import com.tracking.backend.dto.emotion.EmotionRequestDTO;
import com.tracking.backend.dto.emotion.EmotionResponseDTO;
import com.tracking.backend.entity.EmotionLog;
import com.tracking.backend.mapper.EmotionMapper;
import com.tracking.backend.repository.EmotionLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmotionService extends AbstractTrackingService<EmotionLog, EmotionRequestDTO, EmotionResponseDTO> {

    private final EmotionLogRepository emotionLogRepository;

    public EmotionService(EmotionLogRepository emotionLogRepository, EmotionMapper emotionMapper) {
        super(emotionLogRepository, emotionMapper);
        this.emotionLogRepository = emotionLogRepository;
    }

    @Override
    protected List<EmotionLog> fetchRecent() {
        return emotionLogRepository.findTop10ByOrderByCreatedAtDesc();
    }
}
