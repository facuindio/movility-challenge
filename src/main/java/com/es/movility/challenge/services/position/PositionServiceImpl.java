package com.es.movility.challenge.services.position;

import com.es.movility.challenge.dtos.MaxPositionDto;
import com.es.movility.challenge.dtos.Coordinates;
import com.es.movility.challenge.dtos.PositionDto;
import com.es.movility.challenge.enums.CardinalOrientation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class PositionServiceImpl implements PositionService {

    private static final String POSITION_STRING_FORMAT = "%s %s %s";
    private static final String OUT_OF_PLATEAU_MSG = "Mower %s has left the plateau";

    @Override
    public boolean isPositionOutOfBounds(MaxPositionDto maxPositionDto, Coordinates coordinates ,final AtomicInteger counter) {
        if (isPositionOutOfBounds(maxPositionDto, coordinates)) {
            String message = String.format(OUT_OF_PLATEAU_MSG, counter.get());
            log.debug(message);
            return true;
        }
        return false;
    }

    private boolean isPositionOutOfBounds(MaxPositionDto maxPositionDto, Coordinates coordinates){
        return coordinates.isPositionOutOfRange(
                maxPositionDto.getMaxHorizontalPosition(),
                maxPositionDto.getMaxVerticalPosition()
        );
    }

}
