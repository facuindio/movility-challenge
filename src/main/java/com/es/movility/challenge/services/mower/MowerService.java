package com.es.movility.challenge.services.mower;

import com.es.movility.challenge.services.position.Position;
import com.es.movility.challenge.dtos.InputDto;

import java.util.List;

public interface MowerService {
    List<Position> processInput(InputDto inputDto);
}
