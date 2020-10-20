package com.es.movility.challenge.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class CoordinatesDto {

    private int horizontal;
    private int vertical;

    public boolean isPositionOutOfRange(int maxHorizontalPosition, int maxVerticalPosition){
        return isOutOfHorizontalRange(maxHorizontalPosition)
                || isOutOfVerticalRange(maxVerticalPosition);
    }

    private boolean isOutOfHorizontalRange(int maxHorizontalPosition){
        if (horizontal > maxHorizontalPosition || horizontal < 0) return true;
        return false;
    }

    private boolean isOutOfVerticalRange(int maxVerticalPosition) {
        if (vertical > maxVerticalPosition || vertical < 0) return true;
        return false;
    }

}
