package com.example.calculator.service.Impl;

import com.example.calculator.service.CalculatorConsumerService;
import io.spring.training.boot.basedomains.dto.OperationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CalculatorConsumerServiceImpl implements CalculatorConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorConsumerServiceImpl.class);


    @Override
    @KafkaListener(
        topics = "${spring.kafka.topic.name}",
        groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(OperationEvent event) {
        LOGGER.info("Operation request recieved in Calculator service => {}", event.toString());

        // Save de operation in the databese ...
    }
}
