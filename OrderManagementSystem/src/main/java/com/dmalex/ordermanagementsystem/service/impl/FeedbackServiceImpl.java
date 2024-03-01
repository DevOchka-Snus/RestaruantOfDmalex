package com.dmalex.ordermanagementsystem.service.impl;

import com.dmalex.ordermanagementsystem.data.FeedbackRepository;
import com.dmalex.ordermanagementsystem.domain.Feedback;
import com.dmalex.ordermanagementsystem.service.DishService;
import com.dmalex.ordermanagementsystem.service.FeedbackService;
import com.dmalex.ordermanagementsystem.service.PersonService;
import com.dmalex.ordermanagementsystem.web.dto.FeedbackDto;
import lombok.RequiredArgsConstructor;
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
        var author = personService.getById(dto.getAuthorId());
        Feedback feedback = new Feedback();
        feedback.setAuthorId(author.getId());
        feedback.setGrade(dto.getGrade());
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
    public Feedback getByAuthorIdAndDishId(Long authorId, Long dishId) {
        return feedbackRepository.findByAuthorIdAndDishId(authorId, dishId).orElseThrow(() -> new IllegalArgumentException("feedback not found"));
    }

    @Override
    public List<Feedback> getAll() {
        return feedbackRepository.findAll();
    }
}
