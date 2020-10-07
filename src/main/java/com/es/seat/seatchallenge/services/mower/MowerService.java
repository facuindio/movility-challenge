package com.es.seat.seatchallenge.services.mower;

import com.es.seat.seatchallenge.dtos.InputDto;
import com.es.seat.seatchallenge.services.position.Position;

import java.util.List;

public interface MowerService {
    List<Position> processInput(InputDto inputDto);
}
