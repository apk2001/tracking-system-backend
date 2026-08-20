package com.tracking.backend.service;

import com.tracking.backend.dto.thought.ThoughtRequestDTO;
import com.tracking.backend.dto.thought.ThoughtResponseDTO;
import com.tracking.backend.entity.ThoughtLog;
import com.tracking.backend.mapper.ThoughtMapper;
import com.tracking.backend.repository.ThoughtLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThoughtService extends AbstractTrackingService<ThoughtLog, ThoughtRequestDTO, ThoughtResponseDTO> {

    private final ThoughtLogRepository thoughtLogRepository;

    public ThoughtService(ThoughtLogRepository thoughtLogRepository, ThoughtMapper thoughtMapper) {
        super(thoughtLogRepository, thoughtMapper);
        this.thoughtLogRepository = thoughtLogRepository;
    }

    @Override
    protected List<ThoughtLog> fetchRecent() {
        return thoughtLogRepository.findTop10ByOrderByCreatedAtDesc();
    }
}
