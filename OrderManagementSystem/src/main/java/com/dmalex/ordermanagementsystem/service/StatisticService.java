package com.dmalex.ordermanagementsystem.service;

import com.dmalex.ordermanagementsystem.domain.Dish;

import java.math.BigDecimal;
import java.util.List;

public interface StatisticService {
    Integer getAmountOfOrdersAtPeriod();

    BigDecimal getAverageGradeOfDish();

    List<Dish> mostPopularDishes();
}
