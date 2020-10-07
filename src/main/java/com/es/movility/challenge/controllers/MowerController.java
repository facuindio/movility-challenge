package com.es.movility.challenge.controllers;

import com.es.movility.challenge.dtos.InputDto;
import com.es.movility.challenge.services.position.Position;
import com.es.movility.challenge.services.mower.MowerService;
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
