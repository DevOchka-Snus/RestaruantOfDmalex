package com.dmalex.ordermanagementsystem.web.controller.impl;

import com.dmalex.ordermanagementsystem.domain.Feedback;
import com.dmalex.ordermanagementsystem.service.FeedbackService;
import com.dmalex.ordermanagementsystem.web.controller.FeedbackController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedbacks")
@RequiredArgsConstructor
@Tag(
        name = "Feedback Controller",
        description = "Получение информация об отзывах с учетом требований"
)
public class FeedBackControllerImpl implements FeedbackController {
    private final FeedbackService feedbackService;

    @GetMapping("/author/{authorId}")
    @Operation(summary = "Получение всех отзывов, созданных данным пользователем, по его ID")
    @Override
    public ResponseEntity<List<Feedback>> getAllByAuthor(@PathVariable Long authorId) {
        return ResponseEntity.ok(feedbackService.getByAuthorId(authorId));
    }

    @GetMapping("/dish/{dishId}")
    @Operation(summary = "Получение всех отзывов по данному блюду по его ID")
    @Override
    public ResponseEntity<List<Feedback>> getAllByDish(@PathVariable Long dishId) {
        return ResponseEntity.ok(feedbackService.getByDishId(dishId));
    }

    @GetMapping
    @Operation(summary = "Получение всех отзывов")
    @Override
    public ResponseEntity<List<Feedback>> getAll() {
        return ResponseEntity.ok(feedbackService.getAll());
    }
}
