package com.dmalex.ordermanagementsystem.web.controller;

import com.dmalex.ordermanagementsystem.domain.Dish;
import com.dmalex.ordermanagementsystem.domain.Feedback;
import com.dmalex.ordermanagementsystem.web.dto.DishDto;
import com.dmalex.ordermanagementsystem.web.dto.FeedbackDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface DishController {
    ResponseEntity<Dish> create(@Valid @RequestBody DishDto dto);

    ResponseEntity<Dish> update(@PathVariable Long dishId, @Valid @RequestBody DishDto dto);

    ResponseEntity<List<Dish>> getMenu();

    ResponseEntity<Dish> addInMenu(@PathVariable Long dishId);

    ResponseEntity<Dish> removeFromMenu(@PathVariable Long dishId);

    ResponseEntity<Feedback> createFeedback(@PathVariable Long dishId, @Valid @RequestBody FeedbackDto feedbackDto);
}
