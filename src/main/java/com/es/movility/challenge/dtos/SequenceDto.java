package com.es.movility.challenge.dtos;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SequenceDto {

    private PositionDto positionDto;
    private List<String> instructions;

}
