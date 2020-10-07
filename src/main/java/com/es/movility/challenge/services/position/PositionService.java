package com.es.movility.challenge.services.position;

import com.es.movility.challenge.dtos.Coordinates;
import com.es.movility.challenge.dtos.MaxPositionDto;

import java.util.concurrent.atomic.AtomicInteger;

public interface PositionService {

    boolean isPositionOutOfBounds(MaxPositionDto maxPositionDto, Coordinates coordinates , final AtomicInteger counter);

}
