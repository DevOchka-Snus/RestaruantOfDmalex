package com.dmalex.ordermanagementsystem.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalTime;

@Data
@Validated
public class DishDto {
    @NotNull(message = "name must be not null")
    @NotBlank(message = "name must be not blank")
    private String name;
    @NotNull(message = "amount must be not null")
    private Integer amount;
    @NotNull(message = "price must be not null")
    private BigDecimal price;
    @NotNull(message = "time must be not null")
    private Long time;
    @NotNull(message = "isInMenu must be not null")
    private Boolean isInMenu;
}
