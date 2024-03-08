package com.dmalex.ordermanagementsystem.web.dto;

import com.dmalex.ordermanagementsystem.domain.Role;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class PersonDto {
    @NotNull(message = "login must be not null")
    private String login;
    @NotNull(message = "password must be not null")
    private String password;
    @NotNull(message = "password confirmation must be not null")
    private String passwordConfirmation;
    @Hidden
    private Role role;
}
