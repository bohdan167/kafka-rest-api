package com.example.rest.service.Impl;

import com.example.rest.service.OperationProducerService;
import io.spring.training.boot.basedomains.dto.OperationEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OperationProducerServiceImpl implements OperationProducerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperationProducerServiceImpl.class);

    private NewTopic topic;

    private KafkaTemplate<String, OperationEvent> kafkaTemplate;

    @Autowired
    public OperationProducerServiceImpl(NewTopic topic, KafkaTemplate<String, OperationEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(OperationEvent event) {
        LOGGER.info("Operation event => {}", event.toString());

        // get request Id
        String requestId = MDC.get("requestId");

        // create Message
        Message<OperationEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .setHeader("X-Request-ID", requestId)
                .build();


        kafkaTemplate.send(message);
    }
}
