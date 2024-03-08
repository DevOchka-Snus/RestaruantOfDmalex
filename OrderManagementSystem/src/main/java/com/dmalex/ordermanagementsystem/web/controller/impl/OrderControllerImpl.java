package com.dmalex.ordermanagementsystem.web.controller.impl;

import com.dmalex.ordermanagementsystem.domain.Order;
import com.dmalex.ordermanagementsystem.service.RevenueService;
import com.dmalex.ordermanagementsystem.web.controller.OrderController;
import com.dmalex.ordermanagementsystem.web.dto.FixOrderDto;
import com.dmalex.ordermanagementsystem.web.dto.OrderDto;
import com.dmalex.ordermanagementsystem.service.OrderHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Validated
@Tag(
        name = "Order Controller",
        description = "Создание, обновление, отмена и оплата заказов"
)
public class OrderControllerImpl implements OrderController {
    private final OrderHandler orderHandler;
    private final RevenueService revenueService;
    @PostMapping
    @Operation(summary = "Создание заказа")
    @SneakyThrows
    @Override
    public ResponseEntity<Order> create(@Valid @RequestBody OrderDto dto) {
        return ResponseEntity.ok(orderHandler.handleOrder(dto).get());
    }

    @PutMapping("/{orderId}")
    @Operation(summary = "Обновление списка блюд данного заказа по его ID")
    @SneakyThrows
    @Override
    public ResponseEntity<Order> update(@PathVariable Long orderId, @Valid @RequestBody FixOrderDto dto) {
        return ResponseEntity.ok(orderHandler.updateOrder(orderId, dto).get());
    }

    @PutMapping("/cancel/{orderId}")
    @Operation(summary = "Отмена заказа по его ID")
    @SneakyThrows
    @Override
    public ResponseEntity<Order> cancel(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderHandler.cancelOrder(orderId).get());
    }

    @PutMapping("/pay/{orderId}")
    @Operation(summary = "Оплата заказа по его ID")
    @SneakyThrows
    @Override
    public ResponseEntity<Order> pay(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderHandler.payOrder(orderId).get());
    }

    @GetMapping("/admin/revenue")
    @Operation(summary = "Выручка за все время")
    @Override
    public ResponseEntity<BigDecimal> getRevenue() {
        return ResponseEntity.ok(revenueService.getAmountOfRevenueAtAllTime());
    }
}
