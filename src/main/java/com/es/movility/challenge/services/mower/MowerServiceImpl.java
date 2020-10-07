package com.es.movility.challenge.services.mower;

import com.es.movility.challenge.components.strategy.MoverStrategy;
import com.es.movility.challenge.components.transformer.Transformer;
import com.es.movility.challenge.dtos.InputDto;
import com.es.movility.challenge.dtos.MaxPositionDto;
import com.es.movility.challenge.dtos.PositionDto;
import com.es.movility.challenge.dtos.SequenceDto;
import com.es.movility.challenge.services.position.PositionService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
@RequiredArgsConstructor
public class MowerServiceImpl implements MowerService {

    private final Transformer transformer;
    private final MoverStrategy moverStrategy;
    private final PositionService positionService;
    private final MaxPositionDto maxPositionDto;

    public List<PositionDto> processInput(InputDto inputDto) {
        List<PositionDto> finalPositionServices = Lists.newArrayList();
        AtomicInteger counter = new AtomicInteger(0);
        setMaxValues(inputDto);

        log.debug("Processing input...");
        List<SequenceDto> positionsAndInstructions = transformer.transform(inputDto.getInput());
        positionsAndInstructions.forEach(s -> finalPositionServices.add(
                getFinalCoordinates(s, counter))
        );

        log.debug("Input processed successfully. Retrieving mowers coordinates.");
        return finalPositionServices;
    }

    private void setMaxValues(InputDto inputDto) {
        maxPositionDto.setMaxValues(inputDto.getMaxHorizontalPosition(), inputDto.getMaxVerticalPosition());
    }

    private PositionDto getFinalCoordinates(SequenceDto sequenceDto, AtomicInteger counter) {
        AtomicReference<PositionDto> nextPosition = new AtomicReference<>();
        counter.getAndIncrement();
        nextPosition.set(sequenceDto.getPositionDto());
        List<String> movements = sequenceDto.getInstructions();

        log.debug("Processing movements for mower {}", counter.get());
        executeMovements(movements, nextPosition, counter);
        return nextPosition.get();
    }

    private void executeMovements(List<String> movements, AtomicReference<PositionDto> nextPosition, AtomicInteger counter) {
        for (String movement : movements) {
            PositionDto positionDto = nextPosition.get();
            if (positionService.isPositionOutOfBounds(maxPositionDto, positionDto.getCoordinates() ,counter)) break;
            moverStrategy.setNextPosition(movement, nextPosition);
        }
        log.debug("The last position of Mower {}  was: {}", counter.get(), nextPosition.get());
    }

}
