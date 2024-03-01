package com.dmalex.ordermanagementsystem.web.controller.impl;

import com.dmalex.ordermanagementsystem.service.DishService;
import com.dmalex.ordermanagementsystem.service.FeedbackService;
import com.dmalex.ordermanagementsystem.web.controller.DishController;
import com.dmalex.ordermanagementsystem.web.dto.DishDto;
import com.dmalex.ordermanagementsystem.web.dto.FeedbackDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dishes")
@RequiredArgsConstructor
@Validated
public class DishControllerImpl implements DishController {
    private final DishService dishService;
    private final FeedbackService feedbackService;

    @Override
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody DishDto dto) {
        return ResponseEntity.ok(dishService.create(dto));
    }

    @Override
    @PutMapping("/{dishId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@PathVariable Long dishId, @Valid @RequestBody DishDto dto) {
        return ResponseEntity.ok(dishService.update(dishId, dto));
    }

    @Override
    @GetMapping("/menu")
    public ResponseEntity<?> getMenu() {
        return ResponseEntity.ok(dishService.getMenu());
    }

    @PutMapping("/menu/{dishId}/add")
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addInMenu(@PathVariable Long dishId) {
        return ResponseEntity.ok(dishService.updateMenuStatus(dishId, true));
    }

    @PutMapping("/menu/{dishId}/remove")
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> removeFromMenu(@PathVariable Long dishId) {
        return ResponseEntity.ok(dishService.updateMenuStatus(dishId, false));
    }

    @PostMapping("/{dishId}/feedback")
    @Override
    public ResponseEntity<?> createFeedback(@PathVariable Long dishId, @Valid @RequestBody FeedbackDto feedbackDto) {
        return ResponseEntity.ok(feedbackService.create(dishId, feedbackDto));
    }
}
