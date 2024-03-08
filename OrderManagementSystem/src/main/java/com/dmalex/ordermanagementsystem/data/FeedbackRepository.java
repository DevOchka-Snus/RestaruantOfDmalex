package com.dmalex.ordermanagementsystem.data;

import com.dmalex.ordermanagementsystem.domain.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findAll();

    List<Feedback> findAllByAuthorId(Long authorId);

    List<Feedback> findAllByDishId(Long dishId);
}
