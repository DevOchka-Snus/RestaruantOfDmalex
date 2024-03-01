package com.dmalex.ordermanagementsystem.web.controller.impl;

import com.dmalex.ordermanagementsystem.service.FeedbackService;
import com.dmalex.ordermanagementsystem.web.controller.FeedbackController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/feedbacks")
@RequiredArgsConstructor
public class FeedBackControllerImpl implements FeedbackController {
    private final FeedbackService feedbackService;
    @Override
    public ResponseEntity<?> getAllByAuthor(Long authorId) {
        return ResponseEntity.ok(feedbackService.getByAuthorId(authorId));
    }

    @Override
    public ResponseEntity<?> getAllByDish(Long dishId) {
        return ResponseEntity.ok(feedbackService.getByDishId(dishId));
    }

    @Override
    public ResponseEntity<?> getByAuthorAndDish(Long authorId, Long dishId) {
        return ResponseEntity.ok(feedbackService.getByAuthorIdAndDishId(authorId, dishId));
    }

    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(feedbackService.getAll());
    }
}
