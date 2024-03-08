package com.dmalex.ordermanagementsystem.service.impl;

import com.dmalex.ordermanagementsystem.data.FeedbackRepository;
import com.dmalex.ordermanagementsystem.domain.Feedback;
import com.dmalex.ordermanagementsystem.service.DishService;
import com.dmalex.ordermanagementsystem.service.PersonService;
import com.dmalex.ordermanagementsystem.service.FeedbackService;
import com.dmalex.ordermanagementsystem.web.dto.FeedbackDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final PersonService personService;
    private final DishService dishService;
    private final FeedbackRepository feedbackRepository;

    @Transactional
    @Override
    public Feedback create(Long dishId, FeedbackDto dto) {
        var dish = dishService.get(dishId);
        if (!dish.getIsInMenu()) {
            throw new IllegalStateException("dish is not in menu");
        }
        Feedback feedback = new Feedback();
        feedback.setAuthorId(getAuthorId());
        feedback.setGrade(dto.getGrade());
        feedback.setDishId(dishId);
        feedback.setComment(dto.getComment());
        feedback = feedbackRepository.save(feedback);
        return feedback;
    }

    @Override
    public List<Feedback> getByAuthorId(Long authorId) {
        return feedbackRepository.findAllByAuthorId(authorId);
    }

    @Override
    public List<Feedback> getByDishId(Long dishId) {
        return feedbackRepository.findAllByDishId(dishId);
    }

    @Override
    public List<Feedback> getAll() {
        return feedbackRepository.findAll();
    }

    private Long getAuthorId() {
        var currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return personService.getByLogin(currentUser).getId();
    }
}
