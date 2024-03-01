package com.dmalex.ordermanagementsystem.web.controller;

import com.dmalex.ordermanagementsystem.web.dto.DishDto;
import com.dmalex.ordermanagementsystem.web.dto.FeedbackDto;
import org.springframework.http.ResponseEntity;

public interface DishController {
    ResponseEntity<?> create(DishDto dto);
    ResponseEntity<?> update(Long dishId, DishDto dto);
    ResponseEntity<?> getMenu();

    ResponseEntity<?> addInMenu(Long dishId);
    ResponseEntity<?> removeFromMenu(Long dishId);
    ResponseEntity<?> createFeedback(Long dishId, FeedbackDto feedbackDto);
}
