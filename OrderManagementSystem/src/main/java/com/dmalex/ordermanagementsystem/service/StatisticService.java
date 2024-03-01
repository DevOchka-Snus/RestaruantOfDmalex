package com.dmalex.ordermanagementsystem.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface StatisticService {
    Integer getCountOfOrdersForPeriod(LocalDateTime begin, LocalDateTime end);
    BigDecimal getAverageGradeOfDish();

}
