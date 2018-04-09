package xyz.jasonwhite.notes.controllers.advice;

import java.util.Optional;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import xyz.jasonwhite.notes.controllers.TopicController;
import xyz.jasonwhite.notes.repositories.exceptions.TopicNotFoundException;

@ControllerAdvice(assignableTypes = TopicController.class)
@RequestMapping(produces = "application/vnd.error+json")
public class TopicControllerAdvice {
    
    @ExceptionHandler(TopicNotFoundException.class)
    public ResponseEntity<VndErrors> handleBookNotFound(TopicNotFoundException e) {
        return error(e, HttpStatus.NOT_FOUND, String.valueOf(e.getId()));
    }
    
    private <E extends Exception> ResponseEntity<VndErrors> error(
        final E exception, final HttpStatus httpStatus, final String logRef) {
        final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity<>(new VndErrors(logRef, message), httpStatus);
    }

}
