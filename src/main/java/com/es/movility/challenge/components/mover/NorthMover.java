package com.es.movility.challenge.components.mover;

import com.es.movility.challenge.dtos.PositionDto;
import com.es.movility.challenge.enums.CardinalOrientation;
import com.es.movility.challenge.enums.Direction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.es.movility.challenge.enums.Axis.V;

@Slf4j
@Component
public class NorthMover implements Mover {

    public PositionDto processInstructions(PositionDto positionDto, Direction direction) {
        log.debug("Moving from north...");
        switch (direction) {
            case L:
                positionDto.setCardinalOrientation(CardinalOrientation.E);
                return positionDto;
            case R:
                positionDto.setCardinalOrientation(CardinalOrientation.W);
                return positionDto;
            default:
                return move(positionDto, POSITIVE_MOVEMENT, V);
        }
    }

}
