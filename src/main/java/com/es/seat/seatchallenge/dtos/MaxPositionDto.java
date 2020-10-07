package com.es.seat.seatchallenge.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@RequiredArgsConstructor
@Component
public class MaxPositionDto {

    private Integer maxHorizontalPosition;
    private Integer maxVerticalPosition;

    public void setMaxValues(Integer maxHorizontalPosition, Integer maxVerticalPosition){
        setMaxHorizontalPosition(maxHorizontalPosition);
        setMaxVerticalPosition(maxVerticalPosition);
    }

}
