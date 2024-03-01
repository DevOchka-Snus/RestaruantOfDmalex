package com.dmalex.ordermanagementsystem.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface RevenueService {
    BigDecimal getAmountOfRevenueAtPeriod(LocalDateTime begin, LocalDateTime end);
    BigDecimal getAmountOfRevenueAtAllTime();
}
