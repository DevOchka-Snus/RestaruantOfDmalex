package com.dmalex.ordermanagementsystem.web.controller;

import com.dmalex.ordermanagementsystem.domain.Dish;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

public interface StatisticController {
    ResponseEntity<Integer> getAmountOfOrders();

    ResponseEntity<BigDecimal> getAverageGradeOfDishes();

    ResponseEntity<List<Dish>> getMostPopularDishes();
}
