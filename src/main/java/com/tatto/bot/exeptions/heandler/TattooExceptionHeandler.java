package com.tatto.bot.exeptions.heandler;

import com.tatto.bot.exeptions.IncorrectStyleException;
import com.tatto.bot.exeptions.StyleNotFoundException;
import com.tatto.bot.exeptions.WrongModelException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class TattooExceptionHeandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(StyleNotFoundException.class)
    public ResponseEntity<Object> handleStyleNotFoundException(StyleNotFoundException ex) {
        log.error("Style not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex) {
        log.error("Bad request: {} ", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(IncorrectStyleException.class)
    public ResponseEntity<Object> handleIncorrectStyle(IncorrectStyleException ex) {
        log.error("Marked style doesnt exist: {} ", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(WrongModelException.class)
    public ResponseEntity<Object> handleIIncorrectModel(WrongModelException ex) {
        log.error("There is error in the entered field: {} ", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }}
