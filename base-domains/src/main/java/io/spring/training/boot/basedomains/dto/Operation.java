package io.spring.training.boot.basedomains.dto;

import io.spring.training.boot.basedomains.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Operation {

    private BigDecimal a;
    private BigDecimal b;
    private OperationType type;
    private BigDecimal result;

    public Operation(BigDecimal a, BigDecimal b, OperationType type) {
        this.a = a;
        this.b = b;
        this.type = type;
    }
}
