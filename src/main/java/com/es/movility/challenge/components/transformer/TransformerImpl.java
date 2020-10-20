package com.es.movility.challenge.components.transformer;

import com.es.movility.challenge.components.mapper.Mapper;
import com.es.movility.challenge.dtos.SequenceDto;
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

    private final Mapper mapper;
    private static final String regex = "[\\d][\\s][\\d][\\s][A-Z]" + Strings.LINE_SEPARATOR + "(.*)";
    private static final String BLANK_SPACE = " ";

    @Override
    public List<SequenceDto> transform(final String input) {
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(input);

        log.debug("Transforming input...");
        return transform(matcher);
    }

    private List<SequenceDto> transform(Matcher matcher){
        ArrayList<SequenceDto> sequences = new ArrayList<>();

        while (matcher.find()) {
            String[] line = matcher.group(0).split(Strings.LINE_SEPARATOR);
            String[] position = line[0].split(BLANK_SPACE);
            SequenceDto sequenceDto = mapper.toDto(line, position);
            sequences.add(sequenceDto);
        }

        log.debug("Retrieving transformed data.");
        return sequences;
    }

}
