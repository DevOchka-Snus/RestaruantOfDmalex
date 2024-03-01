package com.dmalex.ordermanagementsystem.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Grade grade;
    private String comment;
    private Long dishId;
    private Long authorId;
}
