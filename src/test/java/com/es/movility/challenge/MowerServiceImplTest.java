package com.es.movility.challenge;

import com.es.movility.challenge.dtos.Coordinates;
import com.es.movility.challenge.dtos.InputDto;
import com.es.movility.challenge.services.position.Position;
import com.es.movility.challenge.enums.CardinalOrientation;
import com.es.movility.challenge.services.mower.MowerServiceImpl;
import com.google.common.collect.Lists;
import org.apache.logging.log4j.util.Strings;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MowerServiceImplTest {

    @Autowired
    private MowerServiceImpl mowerService;

    @Test
    public void givenSomeCorrectInputData_ShouldExecuteMovementsSuccessfully() {
        //ARRANGE
        String input = "5 5" + Strings.LINE_SEPARATOR
                + "1 2 N" + Strings.LINE_SEPARATOR
                + "LMLMLMLMM" + Strings.LINE_SEPARATOR
                + "3 3 E" + Strings.LINE_SEPARATOR
                + "MMRMMRMRRM";
        List<Position> expected = Lists.newArrayList();

        Coordinates coordinates = Coordinates.builder()
                .horizontal(1)
                .vertical(3)
                .build();

        Position position = Position.builder()
                .coordinates(coordinates)
                .cardinalOrientation(CardinalOrientation.N)
                .build();
        expected.add(position);


        Coordinates secondCoordinates = Coordinates.builder()
                .horizontal(1)
                .vertical(5)
                .build();

        Position secondPosition = Position.builder()
                .coordinates(secondCoordinates)
                .cardinalOrientation(CardinalOrientation.E)
                .build();
        expected.add(secondPosition);

        //ACT
        List<Position> actual = mowerService.processInput(InputDto.builder().input(input).build());

        //ASSERT
        Assert.assertEquals(expected.get(0).getCardinalOrientation(),
                actual.get(0).getCardinalOrientation());
        Assert.assertEquals(expected.get(0).getCoordinates().getHorizontal(),
                actual.get(0).getCoordinates().getHorizontal());
        Assert.assertEquals(expected.get(0).getCoordinates().getVertical(),
                actual.get(0).getCoordinates().getVertical());
    }

}
