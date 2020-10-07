package com.es.movility.challenge.dtos;

import com.es.movility.challenge.services.position.Position;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SequenceDto {

    private Position position;
    private List<String> instructions;

}
