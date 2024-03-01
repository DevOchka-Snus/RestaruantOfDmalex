package com.dmalex.ordermanagementsystem.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class AuthRequest {
    @NotNull(message = "login must be not null")
    @NotBlank(message = "login must be not blank")
    private String login;
    @NotNull(message = "password must be not null")
    @NotBlank(message = "password must be not blank")
    private String password;
}
