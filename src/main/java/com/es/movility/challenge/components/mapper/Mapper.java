package com.es.movility.challenge.components.mapper;

import com.es.movility.challenge.dtos.SequenceDto;
import com.es.movility.challenge.dtos.Coordinates;
import com.es.movility.challenge.enums.CardinalOrientation;
import com.es.movility.challenge.services.position.Position;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class Mapper {

    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;
    private static final int CARDINAL_ORIENTATION = 2;

    private static final int INSTRUCTIONS = 1;

    public SequenceDto toDto(final String[] rawLine, final String[] position) {
        log.debug("Mapping parsed input...");
        Coordinates coordinates = getCoordinates(position[HORIZONTAL], position[VERTICAL]);
        Position positionService = getPosition(coordinates, position[CARDINAL_ORIENTATION]);
        List instructions = Arrays.asList(rawLine[INSTRUCTIONS].split(Strings.EMPTY));

        log.debug("Retrieving sequence.");
        return SequenceDto.builder()
                .position(positionService)
                .instructions(instructions)
                .build();
    }

    private Position getPosition(Coordinates coordinates, String cardinalOrientationInput){
        log.debug("Mapping coordinates...");
        return  Position.builder()
                .coordinates(coordinates)
                .cardinalOrientation(CardinalOrientation.valueOf(cardinalOrientationInput))
                .build();
    }

    private Coordinates getCoordinates(String maxHorizontal, String maxVertical) {
        log.debug("Mapping position...");
        return Coordinates.builder()
                .horizontal(Integer.parseInt(maxHorizontal))
                .vertical(Integer.parseInt(maxVertical))
                .build();
    }

}