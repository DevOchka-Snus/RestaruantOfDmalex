package com.dmalex.ordermanagementsystem.service;

import com.dmalex.ordermanagementsystem.domain.Order;
import com.dmalex.ordermanagementsystem.domain.OrderStatus;
import com.dmalex.ordermanagementsystem.web.dto.FixOrderDto;
import com.dmalex.ordermanagementsystem.web.dto.OrderDto;

import java.util.List;

public interface OrderService {
    Order create(OrderDto dto, String username);

    Order process(Order order);

    Order update(Long orderId, String username, FixOrderDto dto);

    Order cancel(Long id, String username);

    Order get(Long id);

    OrderStatus getStatus(Long id);

    Order payOrder(Long id, String username);

    List<Order> getAllByStatus(OrderStatus status);

    List<Order> getAllOrders();
}
