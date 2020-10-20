package com.es.movility.challenge.dtos;

import com.es.movility.challenge.enums.CardinalOrientation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class PositionDto implements Serializable {

    private static final String POSITION_STRING_FORMAT = "%s %s %s";

    private CoordinatesDto coordinatesDto;
    private CardinalOrientation cardinalOrientation;

    @Override
    public String toString() {
        return String.format(POSITION_STRING_FORMAT,
                coordinatesDto.getHorizontal(),
                coordinatesDto.getVertical(),
                this.cardinalOrientation.name());
    }

}
