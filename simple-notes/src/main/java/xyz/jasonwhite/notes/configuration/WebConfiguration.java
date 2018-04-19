package xyz.jasonwhite.notes.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import club.caliope.udc.DocumentConverter;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
    }
    
    @Bean
    public DocumentConverter documentConverter() {
        return new DocumentConverter();
    }

}
