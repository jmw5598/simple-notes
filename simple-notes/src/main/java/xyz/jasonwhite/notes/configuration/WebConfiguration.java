package xyz.jasonwhite.notes.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import club.caliope.udc.DocumentConverter;
import xyz.jasonwhite.notes.converters.CsvStringToIterableConverter;
import xyz.jasonwhite.notes.converters.OutputFormatConverter;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
    }
    
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new OutputFormatConverter());
        registry.addConverter(new CsvStringToIterableConverter());
    }
    
    @Bean
    public DocumentConverter documentConverter() {
        return new DocumentConverter();
    }

}
