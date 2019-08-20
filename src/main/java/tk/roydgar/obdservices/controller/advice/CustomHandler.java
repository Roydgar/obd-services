package tk.roydgar.obdservices.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tk.roydgar.obdservices.exception.ResourceNotFoundException;

@ControllerAdvice
public class CustomHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity cannotBuy(ResourceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

}
