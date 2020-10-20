package com.es.movility.challenge.services.position;

import com.es.movility.challenge.entities.Area;
import com.es.movility.challenge.dtos.CoordinatesDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class PositionServiceImpl implements PositionService {

    private static final String POSITION_STRING_FORMAT = "%s %s %s";
    private static final String OUT_OF_AREA_MSG = "Mower %s has left the area";

    @Override
    public boolean isPositionOutOfBounds(Area area, CoordinatesDto coordinatesDto, final AtomicInteger counter) {
        if (isPositionOutOfBounds(area, coordinatesDto)) {
            String message = String.format(OUT_OF_AREA_MSG, counter.get());
            log.debug(message);
            return true;
        }
        return false;
    }

    private boolean isPositionOutOfBounds(Area area, CoordinatesDto coordinatesDto){
        return coordinatesDto.isPositionOutOfRange(
                area.getMaxHorizontal(),
                area.getMaxVertical()
        );
    }

}
