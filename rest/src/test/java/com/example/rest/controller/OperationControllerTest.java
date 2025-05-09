package com.example.rest.controller;

import com.example.rest.service.OperationProducerService;
import com.example.rest.service.OperationService;
import io.spring.training.boot.basedomains.dto.Operation;
import io.spring.training.boot.basedomains.dto.OperationResult;
import io.spring.training.boot.basedomains.enums.OperationType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

@WebMvcTest(OperationController.class)
public class OperationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OperationService operationService;

    @MockitoBean
    private OperationProducerService operationProducerService;

    @Test
    public void sumEndpointTest() throws Exception {
        Operation operation = Operation.builder()
                .a(new BigDecimal(1000000000))
                .b(new BigDecimal(3))
                .type(OperationType.SUM)
                .build();

        given(operationService.calculate(operation))
                .willReturn(new OperationResult(new BigDecimal(1000000003)));

        mockMvc.perform(get("/sum")
                        .param("a", "1000000000")
                        .param("b", "3")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(1000000003));

        operation.setResult(new BigDecimal(1000000003));
        verify(operationService).calculate(operation);
    }

    @Test
    public void subtractEndpointTest() throws Exception {
        Operation operation = Operation.builder()
                .a(new BigDecimal(1000000000))
                .b(new BigDecimal(1))
                .type(OperationType.SUBTRACT)
                .build();

        given(operationService.calculate(operation))
                .willReturn(new OperationResult(new BigDecimal(999999999)));

        mockMvc.perform(get("/subtraction")
                        .param("a", "1000000000")
                        .param("b", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(999999999));

        operation.setResult(new BigDecimal(999999999));
        verify(operationService).calculate(operation);
    }

    @Test
    public void multiplyEndpointTest() throws Exception {

        Operation operation = Operation.builder()
                .a(new BigDecimal(100))
                .b(new BigDecimal(100))
                .type(OperationType.MULTIPLY)
                .build();

        given(operationService.calculate(operation))
                .willReturn(new OperationResult(new BigDecimal(10000)));

        mockMvc.perform(get("/multiplication")
                        .param("a", "100")
                        .param("b", "100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(10000));

        operation.setResult(new BigDecimal(10000));
        verify(operationService).calculate(operation);
    }

    @Test
    public void divideEndpointTest() throws Exception {

        Operation operation = Operation.builder()
                .a(new BigDecimal(5))
                .b(new BigDecimal(3))
                .type(OperationType.DIVIDE)
                .build();

        given(operationService.calculate(operation))
                .willReturn(new OperationResult(new BigDecimal("1.666666667")));

        mockMvc.perform(get("/division")
                        .param("a", "5")
                        .param("b", "3")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(1.666666667));

        operation.setResult(new BigDecimal("1.666666667"));
        verify(operationService).calculate(operation);
    }

    @Test
    public void divisionEndpointTest_zero() throws Exception {
        Operation operation = Operation.builder()
                .a(new BigDecimal(8))
                .b(new BigDecimal(0))
                .type(OperationType.DIVIDE)
                .build();

        mockMvc.perform(get("/division")
                        .param("a", "8")
                        .param("b", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(operationService, never()).calculate(operation);
    }
}
