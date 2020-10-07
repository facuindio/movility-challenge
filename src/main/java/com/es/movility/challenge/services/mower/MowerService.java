package com.es.movility.challenge.services.mower;

import com.es.movility.challenge.dtos.InputDto;
import com.es.movility.challenge.dtos.PositionDto;

import java.util.List;

public interface MowerService {
    List<PositionDto> processInput(InputDto inputDto);
}
