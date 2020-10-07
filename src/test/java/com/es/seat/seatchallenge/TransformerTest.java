package com.es.seat.seatchallenge;

import com.es.seat.seatchallenge.dtos.Coordinates;
import com.es.seat.seatchallenge.services.position.Position;
import com.es.seat.seatchallenge.dtos.SequenceDto;
import com.es.seat.seatchallenge.enums.CardinalOrientation;
import com.es.seat.seatchallenge.components.transformer.Transformer;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TransformerTest {

    @Autowired
    private Transformer transformer;

    @Test
    public void givenSomeInput_applyingRegex_shouldGetSequenceListSuccessfully() {
        //ARRANGE
        List<SequenceDto> expectedList = new ArrayList<SequenceDto>();

        String input = "5 5" + Strings.LINE_SEPARATOR
                + "1 2 N" + Strings.LINE_SEPARATOR
                + "LMLMLMLMM";

        Coordinates coordinates = Coordinates.builder()
                .horizontal(1)
                .vertical(2)
                .build();

        Position initialPosition = Position.builder()
                .coordinates(coordinates)
                .cardinalOrientation(CardinalOrientation.N)
                .build();
        SequenceDto sequenceDto = SequenceDto.builder()
                .position(initialPosition)
                .instructions(Arrays.asList("LMLMLMLMM".split(Strings.EMPTY)))
                .build();
        expectedList.add(sequenceDto);
        SequenceDto expected = expectedList.get(0);

        //ACT
        List<SequenceDto> actualList = transformer.transform(input);
        SequenceDto actual = actualList.get(0);

        //ASSERT
        Assertions.assertEquals(expectedList.size(),
                actualList.size());
        Assertions.assertEquals(expected.getInstructions(),
                actual.getInstructions());
        Assertions.assertEquals(expected.getPosition().getCardinalOrientation(),
                actual.getPosition().getCardinalOrientation());
        Assertions.assertEquals(expected.getPosition().getCoordinates().getHorizontal(),
                actual.getPosition().getCoordinates().getHorizontal());
        Assertions.assertEquals(expected.getPosition().getCoordinates().getVertical(),
                actual.getPosition().getCoordinates().getVertical());
    }

}
