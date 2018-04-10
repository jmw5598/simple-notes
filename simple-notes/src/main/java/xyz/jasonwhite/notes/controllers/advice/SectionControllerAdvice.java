package xyz.jasonwhite.notes.controllers.advice;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import xyz.jasonwhite.notes.repositories.exceptions.SectionNotFoundException;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class SectionControllerAdvice extends AbstractControllerAdvice {
    
    @ExceptionHandler(SectionNotFoundException.class)
    public ResponseEntity<VndErrors> handleBookNotFound(SectionNotFoundException e) {
        return error(e, HttpStatus.NOT_FOUND, String.valueOf(e.getId()));
    }

}
