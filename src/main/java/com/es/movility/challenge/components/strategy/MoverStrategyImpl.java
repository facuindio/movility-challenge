package com.es.movility.challenge.components.strategy;

import com.es.movility.challenge.components.mover.EastMover;
import com.es.movility.challenge.components.mover.NorthMover;
import com.es.movility.challenge.components.mover.SouthMover;
import com.es.movility.challenge.components.mover.WestMover;
import com.es.movility.challenge.dtos.PositionDto;
import com.es.movility.challenge.enums.Direction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@RequiredArgsConstructor
@Component
public class MoverStrategyImpl implements MoverStrategy{

    private final NorthMover northMover;
    private final SouthMover southMover;
    private final EastMover eastMover;
    private final WestMover westMover;

    @Override
    public void setNextPosition(String movement, AtomicReference<PositionDto> position){
        PositionDto nextPosition = movePosition(
                position.get(),
                Direction.valueOf(movement));

        position.set(nextPosition);
        System.out.println(position.get()); //console: check
    }

    private PositionDto movePosition(final PositionDto positionDto, final Direction direction) {
        switch (positionDto.getCardinalOrientation()) {
            case N:
                return northMover.processInstructions(positionDto, direction);
            case S:
                return southMover.processInstructions(positionDto, direction);
            case E:
                return eastMover.processInstructions(positionDto, direction);
            default:
                return westMover.processInstructions(positionDto, direction);
        }
    }

}
