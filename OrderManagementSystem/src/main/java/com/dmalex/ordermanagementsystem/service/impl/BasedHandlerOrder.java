package com.dmalex.ordermanagementsystem.service.impl;

import com.dmalex.ordermanagementsystem.domain.Order;
import com.dmalex.ordermanagementsystem.service.OrderHandler;
import com.dmalex.ordermanagementsystem.service.OrderService;
import com.dmalex.ordermanagementsystem.web.dto.FixOrderDto;
import com.dmalex.ordermanagementsystem.web.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Service
@RequiredArgsConstructor
public class BasedHandlerOrder implements OrderHandler {
    private final OrderService orderService;
    @Autowired
    @Qualifier("orderProcessExecutor")
    private ExecutorService orderProcessExecutor;
    @Autowired
    @Qualifier("addDishAmountExecutor")
    private ExecutorService addDishAmountExecutor;
    @Autowired
    @Qualifier("cancelOrderExecutor")
    private ExecutorService cancelOrderExecutor;

    @Override
    public CompletableFuture<Order> handleOrder(OrderDto dto) {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return CompletableFuture.supplyAsync(() -> orderService.create(dto, username), orderProcessExecutor)
                .thenApply(orderService::process);
    }

    @Override
    public CompletableFuture<Order> updateOrder(Long orderId, FixOrderDto dto) {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return CompletableFuture.supplyAsync(() -> orderService.update(orderId, username, dto), addDishAmountExecutor);
    }

    @Override
    public CompletableFuture<Order> cancelOrder(Long orderId) {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return CompletableFuture.supplyAsync(() -> orderService.cancel(orderId, username), cancelOrderExecutor);
    }

    @Override
    public CompletableFuture<Order> payOrder(Long orderId) {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return CompletableFuture.supplyAsync(() -> orderService.payOrder(orderId, username));
    }
}
