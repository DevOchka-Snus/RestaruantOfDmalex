package com.dmalex.ordermanagementsystem.web.controlleradvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.lang.reflect.UndeclaredThrowableException;

public interface BasedControllerAdvice {
    ResponseEntity<String> handleIllegalState(IllegalStateException e);

    ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e);

    String handleConstraintViolation(ConstraintViolationException e);

    ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException e);

    ResponseEntity<?> handleInvalidFormat(InvalidFormatException e);

    ResponseEntity<?> handleUndeclaredThrowable(UndeclaredThrowableException e);

    String handleException(Exception e);
}
