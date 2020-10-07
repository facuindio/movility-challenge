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
    private String input;

    private String[] getPlateau() {
        return Arrays.asList(this.input
                .split(Strings.LINE_SEPARATOR)).get(0)
                .split(BLANK_SPACE);
    }

    public Integer getMaxHorizontalPosition() {
        return Integer.valueOf(this.getPlateau()[0]);
    }

    public Integer getMaxVerticalPosition() {
        return Integer.valueOf(this.getPlateau()[1]);
    }

}
