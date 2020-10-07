package com.es.movility.challenge.services.position;

import com.es.movility.challenge.dtos.MaxPositionDto;
import com.es.movility.challenge.dtos.Coordinates;
import com.es.movility.challenge.enums.CardinalOrientation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

@Builder
@Getter
@Setter
@Slf4j
public class Position implements Serializable {

    private static final String POSITION_STRING_FORMAT = "%s %s %s";
    private static final String OUT_OF_PLATEAU_MSG = "Mower %s has left the plateau";

    private Coordinates coordinates;
    private CardinalOrientation cardinalOrientation;

    public boolean isPositionOutOfBounds(MaxPositionDto maxPositionDto, final AtomicInteger counter) {
        if (isPositionOutOfBounds(maxPositionDto)) {
            String message = String.format(OUT_OF_PLATEAU_MSG, counter.get());
            log.debug(message);
            return true;
        }
        return false;
    }

    private boolean isPositionOutOfBounds(MaxPositionDto maxPositionDto){
        return coordinates.isPositionOutOfRange(
                maxPositionDto.getMaxHorizontalPosition(),
                maxPositionDto.getMaxVerticalPosition()
        );
    }

    @Override
    public String toString() {
        return String.format(POSITION_STRING_FORMAT,
                coordinates.getHorizontal(),
                coordinates.getVertical(),
                this.cardinalOrientation.name());
    }

}
