package com.dmalex.ordermanagementsystem.web.controller;

import com.dmalex.ordermanagementsystem.domain.Feedback;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface FeedbackController {
    ResponseEntity<List<Feedback>> getAllByAuthor(@PathVariable Long authorId);
    ResponseEntity<List<Feedback>> getAllByDish(@PathVariable Long dishId);
    ResponseEntity<List<Feedback>> getAll();
}
