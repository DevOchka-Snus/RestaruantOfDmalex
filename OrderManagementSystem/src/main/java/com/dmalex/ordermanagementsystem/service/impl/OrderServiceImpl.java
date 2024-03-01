package com.dmalex.ordermanagementsystem.service.impl;

import com.dmalex.ordermanagementsystem.data.DishAmountRepository;
import com.dmalex.ordermanagementsystem.data.OrderRepository;
import com.dmalex.ordermanagementsystem.domain.Order;
import com.dmalex.ordermanagementsystem.domain.OrderStatus;
import com.dmalex.ordermanagementsystem.service.DishService;
import com.dmalex.ordermanagementsystem.service.OrderService;
import com.dmalex.ordermanagementsystem.service.PersonService;
import com.dmalex.ordermanagementsystem.web.dto.FixOrderDto;
import com.dmalex.ordermanagementsystem.web.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final PersonService personService;
    private final DishService dishService;
    private final DishAmountRepository dishAmountRepository;

    @Async
    @Override
    public CompletableFuture<Order> create(OrderDto dto) {
        var client = personService.getById(dto.getClientId());
        var dishAmountList = dishService.getDishOrders(dto.getDishes());
        Order order = new Order();
        order.setClientId(client.getId());
        order.setDishAmountList(dishAmountList);
        order.setDateTime(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.CREATED);
        return CompletableFuture.completedFuture(orderRepository.save(order));
    }

    @Async
    @Transactional
    @Override
    public CompletableFuture<Order> update(Long orderId, FixOrderDto dto) {
        var orderToUpdate = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("order not found"));
        if (orderToUpdate.getOrderStatus().ordinal() > OrderStatus.IN_PROGRESS.ordinal()) {
            throw new IllegalStateException("order can not be updated");
        }
        var newDishAmountList = dishService.getDishOrders(dto.getDishes());
        var dishAmounts = orderToUpdate.getDishAmountList();
        dishAmounts.addAll(newDishAmountList);
        return CompletableFuture.completedFuture(orderRepository.save(orderToUpdate));
    }

    @Async
    @Transactional
    @Override
    public CompletableFuture<Order> cancel(Long id) {
        var order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("order not found"));
        if (order.getOrderStatus().ordinal() > OrderStatus.IN_PROGRESS.ordinal()) {
            throw new IllegalStateException("order can not be canceled");
        }
        order.setOrderStatus(OrderStatus.CANCELED);
        return CompletableFuture.completedFuture(orderRepository.save(order));
    }

    @Override
    public OrderStatus getStatus(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("order not found")).getOrderStatus();
    }

    @Transactional
    @Override
    public Order payOrder(Long id) {
        var order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("order not found"));
        if (order.getOrderStatus() != OrderStatus.COMPLETED) {
            throw new IllegalStateException("order can not be paid");
        }
        order.setOrderStatus(OrderStatus.PAID);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrdersForPeriod(LocalDateTime begin, LocalDateTime end) {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().filter(it -> !(it.getDateTime().isBefore(begin) || it.getDateTime().isAfter(end))).collect(Collectors.toList());
    }

    @Override
    public List<Order> getAllByStatus(OrderStatus status) {
        var result = orderRepository.findAllByOrderStatus(status);
        if (result == null) {
            throw new IllegalStateException("orders with such status do not exist");
        }
        return result;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
