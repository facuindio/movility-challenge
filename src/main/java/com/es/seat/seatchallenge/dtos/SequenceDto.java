package com.es.seat.seatchallenge.dtos;

import com.es.seat.seatchallenge.services.position.Position;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SequenceDto {

    private Position position;
    private List<String> instructions;

}
