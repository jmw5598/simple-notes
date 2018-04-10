package xyz.jasonwhite.notes.controllers.advice;

import java.util.Optional;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AbstractControllerAdvice {
    
    protected <E extends Exception> ResponseEntity<VndErrors> error(
            final E exception, final HttpStatus httpStatus, final String logRef) {
        
        final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity<>(new VndErrors(logRef, message), httpStatus);
    
    }

}
