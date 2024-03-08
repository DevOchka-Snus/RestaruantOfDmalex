package com.dmalex.ordermanagementsystem.service;

import com.dmalex.ordermanagementsystem.domain.Order;
import com.dmalex.ordermanagementsystem.web.dto.FixOrderDto;
import com.dmalex.ordermanagementsystem.web.dto.OrderDto;

import java.util.concurrent.CompletableFuture;

public interface OrderHandler {
    CompletableFuture<Order> handleOrder(OrderDto dto);
    CompletableFuture<Order> updateOrder(Long orderId, FixOrderDto dto);
    CompletableFuture<Order> cancelOrder(Long orderId);
    CompletableFuture<Order> payOrder(Long orderId);
}
