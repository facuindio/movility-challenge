package com.es.movility.challenge.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@RequiredArgsConstructor
@Component
public class Area {

    private Integer maxHorizontal;
    private Integer maxVertical;

    public void setMaxValues(Integer maxHorizontal, Integer maxVertical){
        this.maxHorizontal = maxHorizontal;
        this.maxVertical = maxVertical;
    }

}
