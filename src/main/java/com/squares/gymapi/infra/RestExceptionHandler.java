package com.squares.gymapi.infra;

import com.squares.gymapi.exceptions.MemberAlreadyExistsException;
import com.squares.gymapi.exceptions.MemberNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MemberAlreadyExistsException.class)
    private ResponseEntity<String> memberAlreadyExistsHandler(MemberAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("The member already exists.");
    }

    @ExceptionHandler(MemberNotExistsException.class)
    private ResponseEntity<String> memberNotExistsHandler(MemberNotExistsException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The member does not exists.");
    }
}
