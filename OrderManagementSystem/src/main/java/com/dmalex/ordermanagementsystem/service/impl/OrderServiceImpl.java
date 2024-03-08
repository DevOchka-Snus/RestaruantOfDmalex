package com.dmalex.ordermanagementsystem.service.impl;

import com.dmalex.ordermanagementsystem.data.OrderRepository;
import com.dmalex.ordermanagementsystem.domain.Order;
import com.dmalex.ordermanagementsystem.domain.OrderStatus;
import com.dmalex.ordermanagementsystem.service.DishService;
import com.dmalex.ordermanagementsystem.service.OrderService;
import com.dmalex.ordermanagementsystem.service.PersonService;
import com.dmalex.ordermanagementsystem.web.dto.FixOrderDto;
import com.dmalex.ordermanagementsystem.web.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final PersonService personService;
    private final DishService dishService;

    @Transactional
    @Override
    public Order create(OrderDto dto, String username) {
        var client = personService.getByLogin(username);
        var dishAmountList = dishService.getDishOrders(dto.getDishes());
        Order order = new Order();
        order.setClientId(client.getId());
        order.setDishAmountList(dishAmountList);
        order.setDateTime(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.CREATED);
        return save(order);
    }

    @SneakyThrows
    @Override
    public Order process(Order order) {
        order.setOrderStatus(OrderStatus.IN_PROGRESS);
        save(order);
        var dishAmountList = order.getDishAmountList();
        int begin = 0;
        while (begin != dishAmountList.size()) {
            var dishAmount = dishAmountList.get(begin);
            var dish = dishAmount.getDish();
            for (int i = 0; i < dishAmount.getAmount(); i++) {
                System.out.println("\n\nCooking...\n\n");
                Thread.sleep(dish.getTime());
                var updatedOrder = get(order.getId());
                if (updatedOrder.getOrderStatus() == OrderStatus.CANCELED) {
                    return updatedOrder;
                }
            }
            dishAmountList = get(order.getId()).getDishAmountList();
            System.out.println(dishAmountList.size());
            begin++;
        }
        order = get(order.getId());
        order.setOrderStatus(OrderStatus.COMPLETED);
        return save(order);
    }


    @Transactional
    @Override
    public Order update(Long orderId, String username, FixOrderDto dto) {
        var orderToUpdate = get(orderId);
        var client = personService.getByLogin(username);
        if (!Objects.equals(orderToUpdate.getClientId(), client.getId())) {
            throw new IllegalArgumentException("you do not have the rights to this order");
        }
        if (orderToUpdate.getOrderStatus().ordinal() > OrderStatus.IN_PROGRESS.ordinal()) {
            throw new IllegalStateException("order can not be updated");
        }
        var newDishAmountList = dishService.getDishOrders(dto.getDishes());
        var dishAmounts = orderToUpdate.getDishAmountList();
        dishAmounts.addAll(newDishAmountList);
        return save(orderToUpdate);
    }

    @Transactional
    @Override
    public Order cancel(Long id, String username) {
        var order = get(id);
        var client = personService.getByLogin(username);
        if (!Objects.equals(order.getClientId(), client.getId())) {
            throw new IllegalArgumentException("you do not have the rights to this order");
        }
        if (order.getOrderStatus().ordinal() > OrderStatus.IN_PROGRESS.ordinal()) {
            throw new IllegalStateException("order can not be canceled");
        }
        order.setOrderStatus(OrderStatus.CANCELED);
        return save(order);
    }

    @Override
    public synchronized Order get(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("order %d not found", id)));
    }

    @Override
    public OrderStatus getStatus(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("order not found")).getOrderStatus();
    }

    @Transactional
    @Override
    public Order payOrder(Long id, String username) {
        var order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("order not found"));
        var client = personService.getByLogin(username);
        if (!Objects.equals(order.getClientId(), client.getId())) {
            throw new IllegalArgumentException("you do not have the rights to this order");
        }
        if (order.getOrderStatus() != OrderStatus.COMPLETED) {
            throw new IllegalStateException("order can not be paid");
        }
        order.setOrderStatus(OrderStatus.PAID);
        return orderRepository.save(order);
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

    private synchronized Order save(Order order) {
        return orderRepository.save(order);
    }
}
