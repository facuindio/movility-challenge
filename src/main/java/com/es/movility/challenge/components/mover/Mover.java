package com.es.movility.challenge.components.mover;

import com.es.movility.challenge.dtos.Coordinates;
import com.es.movility.challenge.dtos.PositionDto;
import com.es.movility.challenge.enums.Axis;
import com.es.movility.challenge.enums.CardinalOrientation;
import com.es.movility.challenge.enums.Direction;

public interface Mover {

    int NEGATIVE_MOVEMENT = -1;
    int POSITIVE_MOVEMENT = 1;

    PositionDto processInstructions(PositionDto positionDto, Direction direction);

    default PositionDto move(PositionDto positionDto, int distance, Axis axis){
        Coordinates coordinates = getNewCoordinates(positionDto, distance, axis);
        return retrievePosition(coordinates, positionDto.getCardinalOrientation());
    }

    default Coordinates getNewCoordinates(PositionDto positionDto, int distance, Axis axis){
        int horizontal = positionDto.getCoordinates().getHorizontal();
        int vertical = positionDto.getCoordinates().getVertical();

        if(axis.equals(Axis.H)){
            horizontal = positionDto.getCoordinates().getHorizontal() + distance;
        }
        if(axis.equals(Axis.V)){
            vertical = positionDto.getCoordinates().getVertical() + distance;
        }
        return Coordinates.builder()
                .horizontal(horizontal)
                .vertical(vertical)
                .build();
    }

    default PositionDto retrievePosition(Coordinates coordinates, CardinalOrientation cardinalOrientation) {
        return PositionDto.builder()
                .cardinalOrientation(cardinalOrientation)
                .coordinates(coordinates)
                .build();
    }

}
