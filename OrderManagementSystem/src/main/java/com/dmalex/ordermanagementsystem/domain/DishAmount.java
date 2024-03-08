package com.dmalex.ordermanagementsystem.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class DishAmount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Dish dish;
    private Integer amount;
}
