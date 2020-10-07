package com.es.seat.seatchallenge.components.mover;

import com.es.seat.seatchallenge.dtos.Coordinates;
import com.es.seat.seatchallenge.enums.Axis;
import com.es.seat.seatchallenge.services.position.Position;
import com.es.seat.seatchallenge.enums.CardinalOrientation;
import com.es.seat.seatchallenge.enums.Direction;

public interface Mover {

    int NEGATIVE_MOVEMENT = -1;
    int POSITIVE_MOVEMENT = 1;

    Position processInstructions(Position position, Direction direction);

    default Position move(Position position, int distance, Axis axis){
        Coordinates coordinates = getNewCoordinates(position, distance, axis);
        return retrievePosition(coordinates, position.getCardinalOrientation());
    }

    default Coordinates getNewCoordinates(Position position, int distance, Axis axis){
        int horizontal = position.getCoordinates().getHorizontal();
        int vertical = position.getCoordinates().getVertical();

        if(axis.equals(Axis.H)){
            horizontal = position.getCoordinates().getHorizontal() + distance;
        }
        if(axis.equals(Axis.V)){
            vertical = position.getCoordinates().getVertical() + distance;
        }
        return Coordinates.builder()
                .horizontal(horizontal)
                .vertical(vertical)
                .build();
    }

    default Position retrievePosition(Coordinates coordinates, CardinalOrientation cardinalOrientation) {
        return Position.builder()
                .cardinalOrientation(cardinalOrientation)
                .coordinates(coordinates)
                .build();
    }

}
