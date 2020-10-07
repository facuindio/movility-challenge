package com.es.movility.challenge.services.mover;

import com.es.movility.challenge.dtos.InputDto;
import com.es.movility.challenge.dtos.PositionDto;

import java.util.List;

public interface MoverService {
    List<PositionDto> processInput(InputDto inputDto);
}
