package com.dmalex.ordermanagementsystem.service;

import com.dmalex.ordermanagementsystem.domain.Feedback;
import com.dmalex.ordermanagementsystem.web.dto.FeedbackDto;

import java.util.List;

public interface FeedbackService {
    Feedback create(Long dishId, FeedbackDto dto);
    List<Feedback> getByAuthorId(Long authorId);
    List<Feedback> getByDishId(Long dishId);
    List<Feedback> getAll();
}
