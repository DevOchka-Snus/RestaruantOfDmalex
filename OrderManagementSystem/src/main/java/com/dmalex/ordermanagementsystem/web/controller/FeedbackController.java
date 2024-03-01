package com.dmalex.ordermanagementsystem.web.controller;

import org.springframework.http.ResponseEntity;

public interface FeedbackController {
    ResponseEntity<?> getAllByAuthor(Long author);
    ResponseEntity<?> getAllByDish(Long dishId);
    ResponseEntity<?> getByAuthorAndDish(Long authorId, Long dishId);
    ResponseEntity<?> getAll();
}
