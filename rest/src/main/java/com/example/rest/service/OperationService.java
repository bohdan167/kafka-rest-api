package com.example.rest.service;

import io.spring.training.boot.basedomains.dto.Operation;
import io.spring.training.boot.basedomains.dto.OperationResult;

public interface OperationService {

    OperationResult calculate(Operation operation);
}
