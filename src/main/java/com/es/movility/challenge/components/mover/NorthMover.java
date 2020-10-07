package com.es.movility.challenge.components.mover;

import com.es.movility.challenge.services.position.Position;
import com.es.movility.challenge.enums.CardinalOrientation;
import com.es.movility.challenge.enums.Direction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.es.movility.challenge.enums.Axis.V;

@Slf4j
@Component
public class NorthMover implements Mover {

    public Position processInstructions(Position position, Direction direction) {
        log.debug("Moving from north...");
        switch (direction) {
            case L:
                position.setCardinalOrientation(CardinalOrientation.E);
                return position;
            case R:
                position.setCardinalOrientation(CardinalOrientation.W);
                return position;
            default:
                return move(position, POSITIVE_MOVEMENT, V);
        }
    }

}
