package xyz.jasonwhite.notes.converters;

import java.util.Arrays;

import org.springframework.core.convert.converter.Converter;

public class CsvStringToIterableConverter implements Converter<String, Iterable<String>> {

    public Iterable<String> convert(String source) {
        String[] values = Arrays.stream(source.split(","))
            .map(String::toLowerCase)
            .map(String::trim)
            .distinct()
            .toArray(String[]::new);
        return Arrays.asList(values);
    }

}
