package com.es.seat.seatchallenge.components.mover;

import com.es.seat.seatchallenge.enums.Axis;
import com.es.seat.seatchallenge.services.position.Position;
import com.es.seat.seatchallenge.enums.CardinalOrientation;
import com.es.seat.seatchallenge.enums.Direction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WestMover implements Mover {

    public Position processInstructions(Position position, Direction direction) {
        log.debug("Moving from west...");
        switch (direction) {
            case L:
                position.setCardinalOrientation(CardinalOrientation.N);
                return position;
            case R:
                position.setCardinalOrientation(CardinalOrientation.S);
                return position;
            default:
                return move(position, POSITIVE_MOVEMENT, Axis.H);
        }
    }

}
