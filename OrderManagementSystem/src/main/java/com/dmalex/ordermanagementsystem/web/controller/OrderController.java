package com.dmalex.ordermanagementsystem.web.controller;

import com.dmalex.ordermanagementsystem.domain.Order;
import com.dmalex.ordermanagementsystem.web.dto.FixOrderDto;
import com.dmalex.ordermanagementsystem.web.dto.OrderDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;

public interface OrderController {
    ResponseEntity<Order> create(@Valid @RequestBody OrderDto dto);

    ResponseEntity<Order> update(@PathVariable Long orderId, @Valid @RequestBody FixOrderDto dto);

    ResponseEntity<Order> cancel(@PathVariable Long orderId);

    ResponseEntity<Order> pay(@PathVariable Long orderId);

    ResponseEntity<BigDecimal> getRevenue();
}
