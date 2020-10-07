package com.es.movility.challenge;

import com.es.movility.challenge.components.transformer.Transformer;
import com.es.movility.challenge.components.transformer.TransformerImpl;
import com.es.movility.challenge.dtos.Coordinates;
import com.es.movility.challenge.dtos.PositionDto;
import com.es.movility.challenge.dtos.SequenceDto;
import com.es.movility.challenge.enums.CardinalOrientation;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TransformerImplTest {

    @Autowired
    private TransformerImpl transformerImpl;

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

        PositionDto positionDto = PositionDto.builder()
                .coordinates(coordinates)
                .cardinalOrientation(CardinalOrientation.N)
                .build();
        SequenceDto sequenceDto = SequenceDto.builder()
                .positionDto(positionDto)
                .instructions(Arrays.asList("LMLMLMLMM".split(Strings.EMPTY)))
                .build();
        expectedList.add(sequenceDto);
        SequenceDto expected = expectedList.get(0);

        //ACT
        List<SequenceDto> actualList = transformerImpl.transform(input);
        SequenceDto actual = actualList.get(0);

        //ASSERT
        Assertions.assertEquals(expectedList.size(),
                actualList.size());
        Assertions.assertEquals(expected.getInstructions(),
                actual.getInstructions());
        Assertions.assertEquals(expected.getPositionDto().getCardinalOrientation(),
                actual.getPositionDto().getCardinalOrientation());
        Assertions.assertEquals(expected.getPositionDto().getCoordinates().getHorizontal(),
                actual.getPositionDto().getCoordinates().getHorizontal());
        Assertions.assertEquals(expected.getPositionDto().getCoordinates().getVertical(),
                actual.getPositionDto().getCoordinates().getVertical());
    }

}
