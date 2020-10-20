package com.es.movility.challenge.services.position;

import com.es.movility.challenge.dtos.CoordinatesDto;
import com.es.movility.challenge.entities.Area;

import java.util.concurrent.atomic.AtomicInteger;

public interface PositionService {
    boolean isPositionOutOfBounds(Area area, CoordinatesDto coordinatesDto, final AtomicInteger counter);
}
