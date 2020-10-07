package com.es.seat.seatchallenge.components.strategy;

import com.es.seat.seatchallenge.components.mover.EastMover;
import com.es.seat.seatchallenge.components.mover.NorthMover;
import com.es.seat.seatchallenge.components.mover.SouthMover;
import com.es.seat.seatchallenge.components.mover.WestMover;
import com.es.seat.seatchallenge.services.position.Position;
import com.es.seat.seatchallenge.enums.Direction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@RequiredArgsConstructor
@Component
public class MoverStrategy{

    private final NorthMover northMover;
    private final SouthMover southMover;
    private final EastMover eastMover;
    private final WestMover westMover;

    public void setNextPosition(String movement, AtomicReference<Position> position){
        Position nextPosition = movePosition(
                position.get(),
                Direction.valueOf(movement));

        position.set(nextPosition);
        System.out.println(position.get()); //console: check
    }

    private Position movePosition(final Position position, final Direction direction) {
        switch (position.getCardinalOrientation()) {
            case N:
                return northMover.processInstructions(position, direction);
            case S:
                return southMover.processInstructions(position, direction);
            case E:
                return eastMover.processInstructions(position, direction);
            default:
                return westMover.processInstructions(position, direction);
        }
    }

}
