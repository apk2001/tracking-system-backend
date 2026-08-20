package com.tracking.backend.service;

import com.tracking.backend.dto.weight.WeightRequestDTO;
import com.tracking.backend.dto.weight.WeightResponseDTO;
import com.tracking.backend.entity.WeightLog;
import com.tracking.backend.mapper.WeightMapper;
import com.tracking.backend.repository.WeightLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeightService extends AbstractTrackingService<WeightLog, WeightRequestDTO, WeightResponseDTO> {

    private final WeightLogRepository weightLogRepository;

    public WeightService(WeightLogRepository weightLogRepository, WeightMapper weightMapper) {
        super(weightLogRepository, weightMapper);
        this.weightLogRepository = weightLogRepository;
    }

    @Override
    protected List<WeightLog> fetchRecent() {
        return weightLogRepository.findTop10ByOrderByCreatedAtDesc();
    }
}
