package com.dmalex.ordermanagementsystem.web.controller.impl;

import com.dmalex.ordermanagementsystem.domain.Dish;
import com.dmalex.ordermanagementsystem.domain.Feedback;
import com.dmalex.ordermanagementsystem.service.DishService;
import com.dmalex.ordermanagementsystem.service.FeedbackService;
import com.dmalex.ordermanagementsystem.web.controller.DishController;
import com.dmalex.ordermanagementsystem.web.dto.DishDto;
import com.dmalex.ordermanagementsystem.web.dto.FeedbackDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dishes")
@RequiredArgsConstructor
@Validated
@Tag(
        name = "Dish Controller",
        description = "Создание, редактирование блюд, редактирование меню, создание отзывов на блюда"
)
public class DishControllerImpl implements DishController {
    private final DishService dishService;
    private final FeedbackService feedbackService;

    @PostMapping("/admin")
    @Operation(summary = "Создание новой сущности блюда")
    @Override
    public ResponseEntity<Dish> create(@Valid @RequestBody DishDto dto) {
        return ResponseEntity.ok(dishService.create(dto));
    }

    @PutMapping("/admin/{dishId}")
    @Operation(summary = "Обновление сущности блюда по его ID")
    @Override
    public ResponseEntity<Dish> update(@PathVariable Long dishId, @Valid @RequestBody DishDto dto) {
        return ResponseEntity.ok(dishService.update(dishId, dto));
    }

    @GetMapping("/menu")
    @Operation(summary = "Получение списка всех блюд, находящихся в меню в текущий момент")
    @Override
    public ResponseEntity<List<Dish>> getMenu() {
        return ResponseEntity.ok(dishService.getMenu());
    }

    @PutMapping("/admin/menu/{dishId}/add")
    @Operation(summary = "Добавление блюда в меню по его ID")
    @Override
    public ResponseEntity<Dish> addInMenu(@PathVariable Long dishId) {
        return ResponseEntity.ok(dishService.updateMenuStatus(dishId, true));
    }

    @PutMapping("/admin/menu/{dishId}/remove")
    @Operation(summary = "Удаление блюда из меню по его ID")
    @Override
    public ResponseEntity<Dish> removeFromMenu(@PathVariable Long dishId) {
        return ResponseEntity.ok(dishService.updateMenuStatus(dishId, false));
    }

    @PostMapping("/{dishId}/feedback")
    @Operation(summary = "Создание отзыва на блюда по его ID")
    @Override
    public ResponseEntity<Feedback> createFeedback(@PathVariable Long dishId, @Valid @RequestBody FeedbackDto feedbackDto) {
        return ResponseEntity.ok(feedbackService.create(dishId, feedbackDto));
    }
}
