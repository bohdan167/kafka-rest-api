package com.example.rest.service.Impl;

import com.example.rest.service.OperationService;
import io.spring.training.boot.basedomains.dto.Operation;
import io.spring.training.boot.basedomains.dto.OperationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;

@Service
public class OperationServiceImpl implements OperationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperationServiceImpl.class);

    @Override
    public OperationResult calculate(Operation operation) {
        return switch (operation.getType()) {
            case SUM -> {
                LOGGER.info("Calculating operation sum: {}", operation);
                yield new OperationResult(operation.getA().add(operation.getB()));
            }
            case SUBTRACT -> {
                LOGGER.info("Calculating operation subtract: {}", operation);
                yield new OperationResult(operation.getA().subtract(operation.getB()));
            }
            case MULTIPLY -> {
                LOGGER.info("Calculating operation multiply: {}", operation);
                yield new OperationResult(operation.getA().multiply(operation.getB()));
            }
            case DIVIDE -> {
                LOGGER.info("Calculating operation divide: {}", operation);
                yield new OperationResult(operation.getA().divide(operation.getB(), 9, RoundingMode.HALF_UP));
            }
        };
    }
}
