package com.dmalex.ordermanagementsystem.data;

import com.dmalex.ordermanagementsystem.domain.DishAmount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DishAmountRepository extends JpaRepository<DishAmount, Long> {
    Optional<DishAmount> findByDishId(Long dishId);
}
