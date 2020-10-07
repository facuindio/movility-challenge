package com.es.movility.challenge.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Coordinates {

    private int horizontal;
    private int vertical;

    //TODO: increase coverage
    public boolean isOutOfRange(int maxHorizontalPosition, int maxVerticalPosition){
        return isOutOfHorizontalRange(maxHorizontalPosition)
                || isOutOfVerticalRange(maxVerticalPosition);
    }

    public boolean isOutOfHorizontalRange(int maxHorizontalPosition){
        if (horizontal > maxHorizontalPosition || horizontal < 0) return true;
        return false;
    }

    public boolean isOutOfVerticalRange(int maxVerticalPosition) {
        if (vertical > maxVerticalPosition || vertical < 0) return true;
        return false;
    }

}
