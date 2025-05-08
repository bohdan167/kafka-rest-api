package com.example.rest.controller;

import com.example.rest.service.Impl.OperationServiceImpl;
import com.example.rest.service.OperationService;
import io.spring.training.boot.basedomains.dto.Operation;
import io.spring.training.boot.basedomains.dto.OperationResult;
import io.spring.training.boot.basedomains.enums.OperationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class OperationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperationController.class);

    private final OperationService operationService;

    @Autowired
    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping("/sum")
    public ResponseEntity<OperationResult> sum(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        LOGGER.info("Sum operation started with a: {} and b: {}", a, b);
        Operation operation = new Operation(a, b, OperationType.SUM);
        return ResponseEntity.ok(operationService.calculate(operation));
    }

    @GetMapping("/subtraction")
    public ResponseEntity<OperationResult> sub(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        LOGGER.info("Subtraction operation started with a: {} and b: {}", a, b);
        Operation operation = new Operation(a, b, OperationType.SUBTRACT);
        return ResponseEntity.ok(operationService.calculate(operation));
    }

    @GetMapping("/multiplication")
    public ResponseEntity<OperationResult> mul(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        LOGGER.info("Multiplication operation started with a: {} and b: {}", a, b);
        Operation operation = new Operation(a, b, OperationType.MULTIPLY);
        return ResponseEntity.ok(operationService.calculate(operation));
    }

    @GetMapping("/division")
    public ResponseEntity<OperationResult> div(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        LOGGER.info("Division operation started with a: {} and b: {}", a, b);
        Operation operation = new Operation(a, b, OperationType.DIVIDE);

        if (b.compareTo(BigDecimal.ZERO) == 0) {
            LOGGER.error("Division operation started with zero");
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(operationService.calculate(operation));

    }
}
