package com.dmalex.ordermanagementsystem.service;

import com.dmalex.ordermanagementsystem.domain.Order;
import com.dmalex.ordermanagementsystem.domain.OrderStatus;
import com.dmalex.ordermanagementsystem.web.dto.FixOrderDto;
import com.dmalex.ordermanagementsystem.web.dto.OrderDto;
import org.aspectj.weaver.ast.Or;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface OrderService {
    CompletableFuture<Order> create(OrderDto dto);
    CompletableFuture<Order> update(Long orderId, FixOrderDto dto);
    CompletableFuture<Order> cancel(Long id);
    OrderStatus getStatus(Long id);
    Order payOrder(Long id);
    List<Order> getAllOrdersForPeriod(LocalDateTime begin, LocalDateTime end);
    List<Order> getAllByStatus(OrderStatus status);
    List<Order> getAllOrders();
}
