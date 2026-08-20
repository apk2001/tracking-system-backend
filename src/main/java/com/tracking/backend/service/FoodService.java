package com.tracking.backend.service;

import com.tracking.backend.dto.food.FoodRequestDTO;
import com.tracking.backend.dto.food.FoodResponseDTO;
import com.tracking.backend.entity.FoodLog;
import com.tracking.backend.mapper.FoodMapper;
import com.tracking.backend.repository.FoodLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService extends AbstractTrackingService<FoodLog, FoodRequestDTO, FoodResponseDTO> {

    private final FoodLogRepository foodLogRepository;

    public FoodService(FoodLogRepository foodLogRepository, FoodMapper foodMapper) {
        super(foodLogRepository, foodMapper);
        this.foodLogRepository = foodLogRepository;
    }

    @Override
    protected List<FoodLog> fetchRecent() {
        return foodLogRepository.findTop10ByOrderByCreatedAtDesc();
    }
}
