package com.squares.gymapi.infra;

import com.squares.gymapi.exceptions.MemberAlreadyExistsException;
import com.squares.gymapi.exceptions.MemberNotExistsException;
import com.squares.gymapi.infra.response.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MemberAlreadyExistsException.class)
    private ResponseEntity<ErrorMessage> memberAlreadyExistsHandler(MemberAlreadyExistsException exception) {
        ErrorMessage responseMessage = new ErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseMessage);
    }

    @ExceptionHandler(MemberNotExistsException.class)
    private ResponseEntity<ErrorMessage> memberNotExistsHandler(MemberNotExistsException exception) {
        ErrorMessage responseMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
    }
}
