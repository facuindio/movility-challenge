package com.es.movility.challenge.components.mapper;

import com.es.movility.challenge.dtos.SequenceDto;

public interface Mapper {
    SequenceDto toDto(final String[] rawLine, final String[] position);
}
