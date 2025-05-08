package com.example.rest.service;

import io.spring.training.boot.basedomains.dto.OperationEvent;

public interface OperationProducerService {

    void sendMessage(OperationEvent event);
}
