package com.es.movility.challenge.components.mover;

import com.es.movility.challenge.dtos.Coordinates;
import com.es.movility.challenge.enums.Axis;
import com.es.movility.challenge.services.position.Position;
import com.es.movility.challenge.enums.CardinalOrientation;
import com.es.movility.challenge.enums.Direction;

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
