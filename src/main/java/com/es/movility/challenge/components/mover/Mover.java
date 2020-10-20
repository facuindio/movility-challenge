package com.es.movility.challenge.components.mover;

import com.es.movility.challenge.dtos.CoordinatesDto;
import com.es.movility.challenge.dtos.PositionDto;
import com.es.movility.challenge.enums.Axis;
import com.es.movility.challenge.enums.CardinalOrientation;
import com.es.movility.challenge.enums.Direction;

public interface Mover {

    int NEGATIVE_MOVEMENT = -1;
    int POSITIVE_MOVEMENT = 1;

    PositionDto processInstructions(PositionDto positionDto, Direction direction);

    default PositionDto move(PositionDto positionDto, int distance, Axis axis){
        CoordinatesDto coordinatesDto = getNewCoordinates(positionDto, distance, axis);
        return retrievePosition(coordinatesDto, positionDto.getCardinalOrientation());
    }

    default CoordinatesDto getNewCoordinates(PositionDto positionDto, int distance, Axis axis){
        int horizontal = positionDto.getCoordinatesDto().getHorizontal();
        int vertical = positionDto.getCoordinatesDto().getVertical();

        if(axis.equals(Axis.H)){
            horizontal = positionDto.getCoordinatesDto().getHorizontal() + distance;
        }
        if(axis.equals(Axis.V)){
            vertical = positionDto.getCoordinatesDto().getVertical() + distance;
        }
        return CoordinatesDto.builder()
                .horizontal(horizontal)
                .vertical(vertical)
                .build();
    }

    default PositionDto retrievePosition(CoordinatesDto coordinatesDto, CardinalOrientation cardinalOrientation) {
        return PositionDto.builder()
                .cardinalOrientation(cardinalOrientation)
                .coordinatesDto(coordinatesDto)
                .build();
    }

}
