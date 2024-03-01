package com.dmalex.ordermanagementsystem.data;

import com.dmalex.ordermanagementsystem.domain.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findAll();
    List<Dish> findAllByIsInMenu(boolean isInMenu);
}
