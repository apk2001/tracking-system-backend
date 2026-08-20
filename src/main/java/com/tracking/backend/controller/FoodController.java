package com.tracking.backend.controller;

import com.tracking.backend.dto.food.FoodRequestDTO;
import com.tracking.backend.dto.food.FoodResponseDTO;
import com.tracking.backend.service.FoodService;
import com.tracking.backend.service.TrackingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/food")
public class FoodController extends AbstractTrackingController<FoodRequestDTO, FoodResponseDTO> {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    protected TrackingService<FoodRequestDTO, FoodResponseDTO> service() {
        return foodService;
    }
}
