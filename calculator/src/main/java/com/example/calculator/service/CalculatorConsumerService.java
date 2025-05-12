package com.example.calculator.service;

import io.spring.training.boot.basedomains.dto.OperationEvent;

public interface CalculatorConsumerService {

    void consume(OperationEvent event, String requestId);
}
