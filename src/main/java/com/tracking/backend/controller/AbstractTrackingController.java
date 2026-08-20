package com.tracking.backend.controller;

import com.tracking.backend.service.TrackingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public abstract class AbstractTrackingController<ReqDTO, ResDTO> {

    protected abstract TrackingService<ReqDTO, ResDTO> service();

    @PostMapping
    public ResponseEntity<ResDTO> create(@Valid @RequestBody ReqDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service().create(request));
    }

    @GetMapping("/recent")
    public List<ResDTO> recent() {
        return service().findRecent();
    }
}
