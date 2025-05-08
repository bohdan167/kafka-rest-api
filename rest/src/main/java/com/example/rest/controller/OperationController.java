package com.example.rest.controller;

import com.example.rest.service.OperationProducerService;
import com.example.rest.service.OperationService;
import io.spring.training.boot.basedomains.dto.Operation;
import io.spring.training.boot.basedomains.dto.OperationEvent;
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
    private OperationProducerService operationProducerService;

    @Autowired
    public OperationController(
            OperationService operationService,
            OperationProducerService operationProducerService) {
        this.operationService = operationService;
        this.operationProducerService = operationProducerService;
    }

    @GetMapping("/sum")
    public ResponseEntity<OperationResult> sum(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        Operation operation = new Operation(a, b, OperationType.SUM);
        OperationResult response = operationService.calculate(operation);
        operation.setResult(response.getResult());

        OperationEvent operationEvent = new OperationEvent("sum request", operation);
        operationProducerService.sendMessage(operationEvent);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/subtraction")
    public ResponseEntity<OperationResult> sub(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        Operation operation = new Operation(a, b, OperationType.SUBTRACT);
        OperationResult response = operationService.calculate(operation);
        operation.setResult(response.getResult());

        OperationEvent operationEvent = new OperationEvent("subtraction request", operation);
        operationProducerService.sendMessage(operationEvent);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/multiplication")
    public ResponseEntity<OperationResult> mul(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        Operation operation = new Operation(a, b, OperationType.MULTIPLY);
        OperationResult response = operationService.calculate(operation);
        operation.setResult(response.getResult());

        OperationEvent operationEvent = new OperationEvent("multiplication request", operation);
        operationProducerService.sendMessage(operationEvent);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/division")
    public ResponseEntity<OperationResult> div(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        Operation operation = new Operation(a, b, OperationType.DIVIDE);

        if (b.compareTo(BigDecimal.ZERO) == 0) {
            LOGGER.error("Division operation started with zero");
            return ResponseEntity.badRequest().body(null);
        }
        OperationResult response = operationService.calculate(operation);
        operation.setResult(response.getResult());

        OperationEvent operationEvent = new OperationEvent("division request", operation);
        operationProducerService.sendMessage(operationEvent);

        return ResponseEntity.ok(response);

    }
}
