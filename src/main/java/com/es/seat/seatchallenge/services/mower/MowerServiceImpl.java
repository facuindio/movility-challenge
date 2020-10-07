package com.es.seat.seatchallenge.services.mower;

import com.es.seat.seatchallenge.components.strategy.MoverStrategy;
import com.es.seat.seatchallenge.components.transformer.Transformer;
import com.es.seat.seatchallenge.dtos.InputDto;
import com.es.seat.seatchallenge.dtos.MaxPositionDto;
import com.es.seat.seatchallenge.services.position.Position;
import com.es.seat.seatchallenge.dtos.SequenceDto;
import com.es.seat.seatchallenge.enums.Direction;
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
    private final MaxPositionDto maxPositionDto;

    public List<Position> processInput(InputDto inputDto) {
        List<Position> finalPositions = Lists.newArrayList();
        AtomicInteger counter = new AtomicInteger(0);
        setMaxValues(inputDto);

        log.debug("Processing input...");
        List<SequenceDto> positionsAndInstructions = transformer.transform(inputDto.getInput());
        positionsAndInstructions.forEach(s -> finalPositions.add(this.getFinalCoordinates(s, counter)));

        log.debug("Input processed successfully. Retrieving mowers coordinates.");
        return finalPositions;
    }

    private void setMaxValues(InputDto inputDto) {
        maxPositionDto.setMaxValues(inputDto.getMaxHorizontalPosition(), inputDto.getMaxVerticalPosition());
    }

    private Position getFinalCoordinates(SequenceDto sequenceDto, AtomicInteger counter) {
        AtomicReference<Position> nextPosition = new AtomicReference<>();
        counter.getAndIncrement();
        nextPosition.set(sequenceDto.getPosition());
        List<String> movements = sequenceDto.getInstructions();

        log.debug("Processing movements for mower {}", counter.get());
        this.iterateMovements(movements, nextPosition, counter);
        return nextPosition.get();
    }

    private void iterateMovements(List<String> movements, AtomicReference<Position> nextPosition, AtomicInteger counter) {
        for (String movement : movements) {
            if (nextPosition.get().isNextPositionOutOfBounds(maxPositionDto, counter)) break;
            moverStrategy.setNextPosition(movement, nextPosition);
        }
        log.debug("The last position of Mower {}  was: {}", counter.get(), nextPosition.get());
    }

}
