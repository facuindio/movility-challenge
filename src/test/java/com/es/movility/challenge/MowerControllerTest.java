package com.es.movility.challenge;

import com.es.movility.challenge.dtos.InputDto;
import com.es.movility.challenge.dtos.MaxPositionDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.apache.logging.log4j.util.Strings;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MowerControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MaxPositionDto maxPositionDto;

    @Test
    public void givenChallengeInput_shouldSolveTheProblem() throws Exception {
        //ARRANGE
        String input =
                "5 5" + Strings.LINE_SEPARATOR
                        + "1 2 N" + Strings.LINE_SEPARATOR
                        + "LMLMLMLMM" + Strings.LINE_SEPARATOR
                        + "3 3 E" + Strings.LINE_SEPARATOR
                        + "MMRMMRMRRM";

        val request = InputDto.builder()
                .input(input)
                .build();

        //ACT
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/position")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].coordinates.horizontal").value("1"))
                .andExpect(jsonPath("$[0].coordinates.vertical").value("3"))
                .andExpect(jsonPath("$[0].cardinalOrientation").value("N"))
                .andReturn();
    }

    @Test
    public void givenAllMovementsAndAllCardinals_shouldRetrieveFinalPositionOkWith() throws Exception {
        //ARRANGE
        String input =
                "5 5" + Strings.LINE_SEPARATOR
                        + "1 2 N" + Strings.LINE_SEPARATOR
                        + "LMLMLMLMMR" + Strings.LINE_SEPARATOR
                        + "1 2 S" + Strings.LINE_SEPARATOR
                        + "LMLMLMLMMR" + Strings.LINE_SEPARATOR
                        + "3 3 E" + Strings.LINE_SEPARATOR
                        + "MMRMMRMRRMLL" + Strings.LINE_SEPARATOR
                        + "3 3 W" + Strings.LINE_SEPARATOR
                        + "MMLMMLMLLMR";

        val request = InputDto.builder()
                .input(input)
                .build();

        //ACT
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/position")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].coordinates.horizontal").value("1"))
                .andExpect(jsonPath("$[0].coordinates.vertical").value("3"))
                .andExpect(jsonPath("$[0].cardinalOrientation").value("W"))
                .andReturn();
    }

    @Test
    public void givenCoordinatesThatExceedThePlateauVertically_shouldInformMowerOutOfBounds() throws Exception {
        //ARRANGE
        String input =
                "4 4" + Strings.LINE_SEPARATOR
                        + "1 2 N" + Strings.LINE_SEPARATOR
                        + "MMMMMMM";

        val request = InputDto.builder()
                .input(input)
                .build();

        //ACT
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/position")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].coordinates.horizontal").value("1"))
                .andExpect(jsonPath("$[0].coordinates.vertical").value("5"))
                .andExpect(jsonPath("$[0].cardinalOrientation").value("N"))
                .andReturn();
    }

    @Test
    public void givenCoordinatesThatExceedThePlateauHorizontally_shouldInformMowerOutOfBounds() throws Exception {
        //ARRANGE
        String input =
                "4 4" + Strings.LINE_SEPARATOR
                        + "1 2 W" + Strings.LINE_SEPARATOR
                        + "MMMMMMM";

        val request = InputDto.builder()
                .input(input)
                .build();

        //ACT
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/position")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].coordinates.horizontal").value("5"))
                .andExpect(jsonPath("$[0].coordinates.vertical").value("2"))
                .andExpect(jsonPath("$[0].cardinalOrientation").value("W"))
                .andReturn();
    }

    @Test
    public void givenVerticalCoordinatesMinusZero_shouldInformMowerOutOfBoundsButRetrievePositionAnyway() throws Exception {
        //ARRANGE
        String input =
                "3 3" + Strings.LINE_SEPARATOR
                        + "1 1 S" + Strings.LINE_SEPARATOR
                        + "MMMMMMMM";

        val request = InputDto.builder()
                .input(input)
                .build();

        //ACT AND ASSERT
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/position")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].coordinates.horizontal").value("1"))
                .andExpect(jsonPath("$[0].coordinates.vertical").value("-1"))
                .andExpect(jsonPath("$[0].cardinalOrientation").value("S"))
                .andReturn();
    }

    @Test
    public void givenHorizontalCoordinatesMinusZero_shouldInformMowerOutOfBoundsButRetrievePositionAnyway() throws Exception {
        //ARRANGE
        String input =
                "3 3" + Strings.LINE_SEPARATOR
                        + "1 1 E" + Strings.LINE_SEPARATOR
                        + "MMMMMMM";

        val request = InputDto.builder()
                .input(input)
                .build();

        //ACT
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/position")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].coordinates.horizontal").value("-1"))
                .andExpect(jsonPath("$[0].coordinates.vertical").value("1"))
                .andExpect(jsonPath("$[0].cardinalOrientation").value("E"))
                .andReturn();

    }

    @Test
    public void givenSomeWrongMovementInputCharacters_applyingRegex_shouldThrowException() throws Exception {
        //ARRANGE
        String input =
                "4 4" + Strings.LINE_SEPARATOR
                        + "1 2 W" + Strings.LINE_SEPARATOR
                        + "PPPPP";

        val request = InputDto.builder()
                .input(input)
                .build();

        //ACT AND ASSERT
        Assertions.assertThatCode(() -> mockMvc.perform(post("/api/v1/position")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE)))
                .hasMessage("Request processing failed; nested exception is java.lang.IllegalArgumentException: No enum constant com.es.movility.challenge.enums.Direction.P");
    }

    @Test
    public void givenSomeWrongCardinalInputCharacters_applyingRegex_shouldThrowException() throws Exception {
        //ARRANGE
        String input =
                "4 4" + Strings.LINE_SEPARATOR
                        + "1 2 Q" + Strings.LINE_SEPARATOR
                        + "MRLMRL";

        val request = InputDto.builder()
                .input(input)
                .build();

        //ACT AND ASSERT
        Assertions.assertThatThrownBy(() -> mockMvc.perform(post("/api/v1/position")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE)))
                .hasMessage("Request processing failed; nested exception is java.lang.IllegalArgumentException: No enum constant com.es.movility.challenge.enums.CardinalOrientation.Q");
    }


}
