package com.dmalex.ordermanagementsystem.service.impl;

import com.dmalex.ordermanagementsystem.service.OrderService;
import com.dmalex.ordermanagementsystem.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {
    private final OrderService orderService;
    @Override
    public Integer getCountOfOrdersForPeriod(LocalDateTime begin, LocalDateTime end) {
        return orderService.getAllOrdersForPeriod(begin, end).size();
    }

    @Override
    public BigDecimal getAverageGradeOfDish() {
        return null;
    }
}
