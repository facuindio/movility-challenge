package com.es.movility.challenge.components.transformer;

import com.es.movility.challenge.dtos.SequenceDto;
import com.es.movility.challenge.components.mapper.MapperImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
@Component
public class TransformerImpl implements Transformer{

    private final MapperImpl mapperImpl;
    private static final String regex = "[\\d][\\s][\\d][\\s][A-Z]" + Strings.LINE_SEPARATOR + "(.*)";
    private static final String BLANK_SPACE = " ";

    @Override
    public List<SequenceDto> transform(final String input) {
        ArrayList<SequenceDto> sequences = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(input);

        log.debug("Transforming input...");
        return transform(matcher, sequences);
    }

    private List<SequenceDto> transform(Matcher matcher, ArrayList<SequenceDto> sequences){
        while (matcher.find()) {
            String[] rawLine = matcher.group(0).split(Strings.LINE_SEPARATOR);
            String[] position = rawLine[0].split(BLANK_SPACE);
            SequenceDto sequenceDto = mapperImpl.toDto(rawLine, position);
            sequences.add(sequenceDto);
        }

        log.debug("Retrieving transformed data.");
        return sequences;
    }

}
