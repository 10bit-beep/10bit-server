package com.tenbit.beep.common.handler;

import com.tenbit.beep.common.exception.AlreadyUsingIdException;
import com.tenbit.beep.common.exception.IllegalArgumentsException;
import com.tenbit.beep.common.exception.ValueMissingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyUsingIdException.class)
    public ResponseEntity<String> handleAlready(AlreadyUsingIdException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentsException.class)
    public ResponseEntity<String> handleIllegal(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ValueMissingException.class)
    public ResponseEntity<String> handleValueMissing(ValueMissingException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
