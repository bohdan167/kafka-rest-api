package com.example.calculator.service.Impl;

import com.example.calculator.service.CalculatorConsumerService;
import io.spring.training.boot.basedomains.dto.OperationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import static io.spring.training.boot.basedomains.constant.RequestIdValues.REQUEST_ID_HEADER;

@Service
public class CalculatorConsumerServiceImpl implements CalculatorConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorConsumerServiceImpl.class);


    @Override
    @KafkaListener(
        topics = "${spring.kafka.topic.name}",
        groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(
            @Payload OperationEvent event,
            @Header(name = REQUEST_ID_HEADER) String requestId
    ) {
        try {
            LOGGER.info("Processing operation request [{}]: {}", requestId, event.toString());
        } finally {
            MDC.clear();
        }
    }
}
