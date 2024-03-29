package com.dmalex.ordermanagementsystem.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer amount;
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
    private Long time;
    private Boolean isInMenu;
}
