package com.es.movility.challenge.components.mapper;

import com.es.movility.challenge.dtos.PositionDto;
import com.es.movility.challenge.dtos.SequenceDto;
import com.es.movility.challenge.dtos.CoordinatesDto;
import com.es.movility.challenge.enums.CardinalOrientation;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class MapperImpl implements Mapper {

    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;
    private static final int CARDINAL_ORIENTATION = 2;

    private static final int INSTRUCTIONS = 1;

    @Override
    public SequenceDto toDto(final String[] line, final String[] position) {
        log.debug("Mapping parsed input...");
        CoordinatesDto coordinatesDto = getCoordinates(position[HORIZONTAL], position[VERTICAL]);
        PositionDto positionDto = getPosition(coordinatesDto, position[CARDINAL_ORIENTATION]);
        List instructions = Arrays.asList(line[INSTRUCTIONS].split(Strings.EMPTY));

        log.debug("Retrieving sequence.");
        return SequenceDto.builder()
                .positionDto(positionDto)
                .instructions(instructions)
                .build();
    }

    private PositionDto getPosition(CoordinatesDto coordinatesDto, String cardinalOrientationInput){
        log.debug("Mapping coordinates...");
        return  PositionDto.builder()
                .coordinatesDto(coordinatesDto)
                .cardinalOrientation(CardinalOrientation.valueOf(cardinalOrientationInput))
                .build();
    }

    private CoordinatesDto getCoordinates(String maxHorizontal, String maxVertical) {
        log.debug("Mapping position...");
        return CoordinatesDto.builder()
                .horizontal(Integer.parseInt(maxHorizontal))
                .vertical(Integer.parseInt(maxVertical))
                .build();
    }

}
