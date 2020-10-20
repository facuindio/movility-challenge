package com.es.movility.challenge.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InputDto {

    private static final String BLANK_SPACE = " ";
    private static final int HORIZONTAL_INDEX = 0;
    private static final int VERTICAL_INDEX = 1;
    private String input;

    private String[] getArea() {
        return getFirstLine().split(BLANK_SPACE);
    }

    private String getFirstLine(){
        return this.input.split(Strings.LINE_SEPARATOR)[0];
    }

    public Integer getMaxHorizontalPosition() {
        return Integer.valueOf(this.getArea()[HORIZONTAL_INDEX]);
    }

    public Integer getMaxVerticalPosition() {
        return Integer.valueOf(this.getArea()[VERTICAL_INDEX]);
    }

}
