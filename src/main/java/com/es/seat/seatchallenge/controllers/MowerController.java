package com.es.seat.seatchallenge.controllers;

import com.es.seat.seatchallenge.dtos.InputDto;
import com.es.seat.seatchallenge.services.position.Position;
import com.es.seat.seatchallenge.services.mower.MowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/position")
public class MowerController {

    private final MowerService mowerService;

    @PostMapping
    public List<Position> getFinalPositions(@RequestBody InputDto inputDto) {
        return mowerService.processInput(inputDto);
    }

}
