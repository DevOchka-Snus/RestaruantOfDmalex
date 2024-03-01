package com.dmalex.ordermanagementsystem.service.impl;

import com.dmalex.ordermanagementsystem.domain.OrderStatus;
import com.dmalex.ordermanagementsystem.service.DishService;
import com.dmalex.ordermanagementsystem.service.OrderService;
import com.dmalex.ordermanagementsystem.service.RevenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RevenueServiceImpl implements RevenueService {
    private final DishService dishService;
    private final OrderService orderService;

    @Override
    public BigDecimal getAmountOfRevenueAtPeriod(LocalDateTime begin, LocalDateTime end) {
        var orders = orderService.getAllByStatus(OrderStatus.PAID);
        BigDecimal sum = BigDecimal.ZERO;
        for (var order : orders) {
            if (order.getDateTime().isBefore(begin) || order.getDateTime().isAfter(end)) {
                continue;
            }
            BigDecimal sumInOrder = BigDecimal.ZERO;
            for (var dishAmount : order.getDishAmountList()) {
                var dish = dishService.get(dishAmount.getDishId());
                var price = dish.getPrice().multiply(BigDecimal.valueOf(dishAmount.getAmount())).setScale(2, RoundingMode.HALF_UP);
                sumInOrder = sumInOrder.add(price).setScale(2, RoundingMode.HALF_UP);
            }
            sum = sum.add(sumInOrder).setScale(2, RoundingMode.HALF_UP);
        }
        return sum;
    }

    @Override
    public BigDecimal getAmountOfRevenueAtAllTime() {
        var orders = orderService.getAllByStatus(OrderStatus.PAID);
        BigDecimal sum = BigDecimal.ZERO;
        for (var order : orders) {
            BigDecimal sumInOrder = BigDecimal.ZERO;
            for (var dishAmount : order.getDishAmountList()) {
                var dish = dishService.get(dishAmount.getDishId());
                var price = dish.getPrice().multiply(BigDecimal.valueOf(dishAmount.getAmount())).setScale(2, RoundingMode.HALF_UP);
                sumInOrder = sumInOrder.add(price).setScale(2, RoundingMode.HALF_UP);
            }
            sum = sum.add(sumInOrder).setScale(2, RoundingMode.HALF_UP);
        }
        return sum;
    }
}
