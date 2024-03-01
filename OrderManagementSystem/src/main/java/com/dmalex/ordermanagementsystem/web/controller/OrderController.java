package com.dmalex.ordermanagementsystem.web.controller;

import com.dmalex.ordermanagementsystem.web.dto.FixOrderDto;
import com.dmalex.ordermanagementsystem.web.dto.OrderDto;
import org.springframework.http.ResponseEntity;

public interface OrderController {
    ResponseEntity<?> create(OrderDto dto);
    ResponseEntity<?> update(Long orderId, FixOrderDto dto);
    ResponseEntity<?> cancel(Long orderId);
    ResponseEntity<?> pay(Long orderId);

}
