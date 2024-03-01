package com.dmalex.ordermanagementsystem.data;

import com.dmalex.ordermanagementsystem.domain.Order;
import com.dmalex.ordermanagementsystem.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    void deleteById(@NonNull Long id);
    List<Order> findAllByOrderStatus(@NonNull OrderStatus status);
    List<Order> findAll();
}
