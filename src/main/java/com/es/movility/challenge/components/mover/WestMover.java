package com.es.movility.challenge.components.mover;

import com.es.movility.challenge.dtos.PositionDto;
import com.es.movility.challenge.enums.Axis;
import com.es.movility.challenge.enums.CardinalOrientation;
import com.es.movility.challenge.enums.Direction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WestMover implements Mover {

    public PositionDto processInstructions(PositionDto positionDto, Direction direction) {
        log.debug("Moving from west...");
        switch (direction) {
            case L:
                positionDto.setCardinalOrientation(CardinalOrientation.N);
                return positionDto;
            case R:
                positionDto.setCardinalOrientation(CardinalOrientation.S);
                return positionDto;
            default:
                return move(positionDto, POSITIVE_MOVEMENT, Axis.H);
        }
    }

}
