package com.es.movility.challenge.components.transformer;

import com.es.movility.challenge.dtos.SequenceDto;

import java.util.List;

public interface Transformer {

    List<SequenceDto> transform(final String input);

    }
