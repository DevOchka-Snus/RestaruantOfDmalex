package com.dmalex.ordermanagementsystem.web.controller.impl;

import com.dmalex.ordermanagementsystem.domain.Dish;
import com.dmalex.ordermanagementsystem.service.StatisticService;
import com.dmalex.ordermanagementsystem.web.controller.StatisticController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/statistic/admin")
@RequiredArgsConstructor
@Tag(
        name = "Statistic Controller",
        description = "Получение статистики по заказам и блюдам"
)
@Validated
public class StatisticControllerImpl implements StatisticController {
    private final StatisticService statisticService;
    @GetMapping("/amount")
    @Operation(summary = "Получение количества заказов за указанный период")
    @Override
    public ResponseEntity<Integer> getAmountOfOrders() {
        return ResponseEntity.ok(statisticService.getAmountOfOrdersAtPeriod());
    }

    @GetMapping("/grade")
    @Operation(summary = "Получение средней оценки блюд")
    @Override
    public ResponseEntity<BigDecimal> getAverageGradeOfDishes() {
        return ResponseEntity.ok(statisticService.getAverageGradeOfDish());
    }

    @GetMapping("/popular")
    @Operation(summary = "Получение списка самых популярных блюд")
    @Override
    public ResponseEntity<List<Dish>> getMostPopularDishes() {
        return ResponseEntity.ok(statisticService.mostPopularDishes());
    }
}
