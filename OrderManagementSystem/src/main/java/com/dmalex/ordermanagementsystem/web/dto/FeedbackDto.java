package com.dmalex.ordermanagementsystem.web.dto;

import com.dmalex.ordermanagementsystem.domain.Grade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class FeedbackDto {
    @NotNull(message = "grade must be not null")
    private Grade grade;
    @NotNull(message = "comment must be not null")
    @NotBlank(message = "comment must be not blank")
    private String comment;
}
