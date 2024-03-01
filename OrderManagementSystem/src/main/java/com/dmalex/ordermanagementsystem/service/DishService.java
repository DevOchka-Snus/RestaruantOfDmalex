package com.dmalex.ordermanagementsystem.service;

import com.dmalex.ordermanagementsystem.domain.Dish;
import com.dmalex.ordermanagementsystem.domain.DishAmount;
import com.dmalex.ordermanagementsystem.domain.Feedback;
import com.dmalex.ordermanagementsystem.web.dto.DishDto;
import com.dmalex.ordermanagementsystem.web.dto.FeedbackDto;

import java.util.List;
import java.util.Map;

public interface DishService {
    Dish create(DishDto dto);
    Dish update(Long id, DishDto dto);

    List<Dish> getMenu();
    Dish updateMenuStatus(Long dishId, boolean menuStatus);

    Dish get(Long id);
    List<DishAmount> getDishOrders(final Map<Long, Integer> dto);

    boolean isExists(Long id, Integer amount);
}
