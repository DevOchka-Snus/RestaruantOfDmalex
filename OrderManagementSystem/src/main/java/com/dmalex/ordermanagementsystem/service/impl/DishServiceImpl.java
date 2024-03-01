package com.dmalex.ordermanagementsystem.service.impl;

import com.dmalex.ordermanagementsystem.data.DishAmountRepository;
import com.dmalex.ordermanagementsystem.data.DishRepository;
import com.dmalex.ordermanagementsystem.domain.Dish;
import com.dmalex.ordermanagementsystem.domain.DishAmount;
import com.dmalex.ordermanagementsystem.service.DishService;
import com.dmalex.ordermanagementsystem.web.dto.DishDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;
    private final DishAmountRepository dishAmountRepository;

    @Override
    public Dish create(DishDto dto) {
        Dish dish = new Dish();
        dish.setName(dto.getName());
        dish.setAmount(dish.getAmount());
        dish.setPrice(dto.getPrice());
        dish.setTime(dto.getTime());
        dish.setIsInMenu(dto.getIsInMenu());
        return dishRepository.save(dish);
    }

    @Override
    public Dish update(Long id, DishDto dto) {
        var dishToUpdate = dishRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("dish not found"));
        dishToUpdate.setIsInMenu(dto.getIsInMenu());
        dishToUpdate.setName(dto.getName());
        dishToUpdate.setAmount(dto.getAmount());
        dishToUpdate.setPrice(dto.getPrice());
        dishToUpdate.setTime(dto.getTime());
        dishRepository.save(dishToUpdate);
        return dishToUpdate;
    }

    @Override
    public List<Dish> getMenu() {
        return dishRepository.findAllByIsInMenu(true);
    }

    @Transactional
    @Override
    public Dish updateMenuStatus(Long dishId, boolean menuStatus) {
        var dish = dishRepository.findById(dishId).orElseThrow(() -> new IllegalArgumentException("dish not found"));
        dish.setIsInMenu(menuStatus);

        return dishRepository.save(dish);
    }

    @Override
    public Dish get(Long id) {
        return dishRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("dish not found"));
    }

    @Transactional
    @Override
    public List<DishAmount> getDishOrders(final Map<Long, Integer> dtoMap) {
        List<DishAmount> dishAmountList = new ArrayList<>();
        for (var dishOrder : dtoMap.entrySet()) {
            var dish = get(dishOrder.getKey());
            if (!dish.getIsInMenu()) {
                throw new IllegalArgumentException("dish is not in order");
            }
            if (dish.getAmount() < dishOrder.getValue()) {
                throw new IllegalStateException("dish amount is not enough");
            }
            dish.setAmount(dish.getAmount() - dishOrder.getValue());
            if (dish.getAmount() == 0) {
                dish.setIsInMenu(false);
            }
            dishRepository.save(dish);

            var dishAmount = new DishAmount();
            dishAmount.setDishId(dishOrder.getKey());
            dishAmount.setAmount(dishOrder.getValue());

            dishAmountList.add(dishAmountRepository.save(dishAmount));
        }

        return dishAmountList;
    }


    @Override
    public boolean isExists(Long id, Integer amount) {
        var dish = dishRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("dish not found"));
        return dish.getIsInMenu() && dish.getAmount() >= amount;
    }
}
