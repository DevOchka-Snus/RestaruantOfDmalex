package com.dmalex.ordermanagementsystem.web.controller.impl;

import com.dmalex.ordermanagementsystem.web.controller.OrderController;
import com.dmalex.ordermanagementsystem.web.dto.FixOrderDto;
import com.dmalex.ordermanagementsystem.web.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Validated
public class OrderControllerImpl implements OrderController {
    
    @Override
    public ResponseEntity<?> create(OrderDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(Long orderId, FixOrderDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<?> cancel(Long orderId) {
        return null;
    }

    @Override
    public ResponseEntity<?> pay(Long orderId) {
        return null;
    }
}
