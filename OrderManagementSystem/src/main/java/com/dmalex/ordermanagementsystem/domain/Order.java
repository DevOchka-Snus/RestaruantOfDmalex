package com.dmalex.ordermanagementsystem.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "dish_amount_id"))
    private List<DishAmount> dishAmountList;
    private Long clientId;
    private LocalDateTime dateTime;
}
