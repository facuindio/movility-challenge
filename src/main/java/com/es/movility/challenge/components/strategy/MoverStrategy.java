package com.es.movility.challenge.components.strategy;

import com.es.movility.challenge.dtos.PositionDto;

import java.util.concurrent.atomic.AtomicReference;

public interface MoverStrategy {
    void setNextPosition(String movement, AtomicReference<PositionDto> position);
}
