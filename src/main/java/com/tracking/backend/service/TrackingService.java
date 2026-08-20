package com.tracking.backend.service;

import java.util.List;

public interface TrackingService<ReqDTO, ResDTO> {

    ResDTO create(ReqDTO request);

    List<ResDTO> findRecent();
}
