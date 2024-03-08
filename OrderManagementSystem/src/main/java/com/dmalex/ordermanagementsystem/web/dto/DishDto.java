package com.dmalex.ordermanagementsystem.web.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DishDto {

    @NotNull(message = "name must be not null")
    @NotBlank(message = "name must be not blank")
    private String name;

    @NotNull(message = "amount must be not null")
    @Min(value = 1, message = "amount must be greater than 1")
    private Integer amount;

    @NotNull(message = "price must be not null")
    @DecimalMin(value = "0.00", message = "price must be greater than 0.00")
    private BigDecimal price;

    @NotNull(message = "time must be not null")
    @Min(value = 1, message = "time must be greater than 1")
    private Long time;

    @NotNull(message = "isInMenu must be not null")
    private Boolean isInMenu;
}
