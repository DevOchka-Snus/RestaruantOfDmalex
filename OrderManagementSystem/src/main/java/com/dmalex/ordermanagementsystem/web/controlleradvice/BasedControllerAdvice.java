package com.dmalex.ordermanagementsystem.web.controlleradvice;

import org.springframework.http.ResponseEntity;

public interface BasedControllerAdvice {
    ResponseEntity<?> handleIllegalState(IllegalStateException e);
    ResponseEntity<?> handleIllegalArgument(IllegalArgumentException e);
    ResponseEntity<?> handleException(Exception e);
}
