package xyz.jasonwhite.notes.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import club.caliope.udc.OutputFormat;

public class OutputFormatConverter implements Converter<String, OutputFormat> {

    @Override
    public OutputFormat convert(String source) {
        try {
            return OutputFormat.valueOf(source.toUpperCase());
        } catch (MethodArgumentTypeMismatchException e) {
            return null;
        }
    }
    
}
